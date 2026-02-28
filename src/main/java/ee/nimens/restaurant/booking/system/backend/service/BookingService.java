package ee.nimens.restaurant.booking.system.backend.service;

import ee.nimens.restaurant.booking.system.backend.dto.booking.BookingDto;
import ee.nimens.restaurant.booking.system.backend.dto.request.booking.CreateBookingRequestDto;
import ee.nimens.restaurant.booking.system.backend.entity.BookingEntity;
import ee.nimens.restaurant.booking.system.backend.entity.TableEntity;
import ee.nimens.restaurant.booking.system.backend.exception.booking.InvalidBookingTimeException;
import ee.nimens.restaurant.booking.system.backend.repository.BookingRepository;
import ee.nimens.restaurant.booking.system.backend.util.mapper.BookingMapper;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
        ZonedDateTime truncatedStartsAt = dto.startsAt().truncatedTo(ChronoUnit.MINUTES);
        ZonedDateTime truncatedEndsAt = dto.endsAt().truncatedTo(ChronoUnit.MINUTES);

        if (truncatedStartsAt.isAfter(truncatedEndsAt)) {
            throw new InvalidBookingTimeException("Start timing should be before end timing");
        }

        TableEntity table = tableService.findById(dto.tableId());
        BookingEntity createdEntity = bookingRepository.save(BookingEntity.builder()
            .tableId(table.getId())
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
    public List<BookingEntity> findBetween(ZonedDateTime start, ZonedDateTime end) {
        return bookingRepository.findAllBetween(start, end);
    }

}
