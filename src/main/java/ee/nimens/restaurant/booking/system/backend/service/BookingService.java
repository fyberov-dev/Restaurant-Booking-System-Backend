package ee.nimens.restaurant.booking.system.backend.service;

import ee.nimens.restaurant.booking.system.backend.dto.booking.BookingDto;
import ee.nimens.restaurant.booking.system.backend.dto.request.booking.CreateBookingRequestDto;
import ee.nimens.restaurant.booking.system.backend.entity.BookingEntity;
import ee.nimens.restaurant.booking.system.backend.exception.booking.InvalidBookingTimeException;
import ee.nimens.restaurant.booking.system.backend.exception.booking.TableBadTimingsException;
import ee.nimens.restaurant.booking.system.backend.repository.BookingRepository;
import ee.nimens.restaurant.booking.system.backend.util.mapper.BookingMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final TableService tableService;

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    /**
     * Create new booking.
     * <p></p>
     * Booking should have:
     *  - Existing table
     *  - End time is not before start time
     * <p></p>
     * Booking timestamp should be in format: hh:mm:00.000
     *
     * @param dto data to create a booking
     * @return dto of a created booking
     */
    public BookingDto create(CreateBookingRequestDto dto) {
        Instant truncatedStartsAt = formatDate(dto.startsAt());
        Instant truncatedEndsAt = formatDate(dto.endsAt());

        checkIfPossibleToBook(dto.tableId(), truncatedStartsAt, truncatedEndsAt);

        BookingEntity createdEntity = bookingRepository.save(BookingEntity.builder()
            .tableId(dto.tableId())
            .phone(dto.phone())
            .email(dto.email())
            .startsAt(truncatedStartsAt)
            .endsAt(truncatedEndsAt)
            .build());

        log.debug("Created new booking. id={}, tableId={}, start={}, end={}",
            createdEntity.getId(),
            createdEntity.getTableId(),
            createdEntity.getStartsAt(),
            createdEntity.getEndsAt()
        );

        return bookingMapper.toDto(createdEntity);
    }

    /**
     * Get bookings between two dates (include).
     *
     * @param start bookings should be after this ZonedDateTime
     * @param end bookings should be before this ZonedDateTime
     * @return found bookings
     */
    public List<BookingEntity> findBetween(Instant start, Instant end) {
        return bookingRepository.findAllBetween(start, end);
    }

    public Optional<BookingEntity> findTableBookingsBetween(Long tableId, Instant start, Instant end) {
        return bookingRepository.findTableBookingsBetween(tableId, start, end);
    }

    private Instant formatDate(Instant date) {
        return date.truncatedTo(ChronoUnit.MINUTES)
            .atZone(TimeZone.getDefault().toZoneId())
            .toInstant();
    }

    private void checkIfPossibleToBook(Long tableId, Instant start, Instant end) {
        Instant now = Instant.now();

        if (start.isBefore(now)) {
            throw new TableBadTimingsException("Reservation date cannot be in the past");
        }

        if (start.isAfter(end)) {
            throw new InvalidBookingTimeException("Start timing should be before end timing");
        }

        if (findTableBookingsBetween(tableId, start, end).isPresent()) {
            throw new TableBadTimingsException("Table can't be booked at this timings");
        }

        tableService.findById(tableId);
    }

}
