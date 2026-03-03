package ee.nimens.restaurant.booking.system.backend.service;

import ee.nimens.restaurant.booking.system.backend.dto.booking.TableRating;
import ee.nimens.restaurant.booking.system.backend.dto.request.booking.FilterBookingDto;
import ee.nimens.restaurant.booking.system.backend.entity.BookingEntity;
import ee.nimens.restaurant.booking.system.backend.entity.TableEntity;
import ee.nimens.restaurant.booking.system.backend.entity.TableTypeEntity;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final BookingService bookingService;
    private final TableService tableService;

    public Map<Long, TableRating> getRating(FilterBookingDto dto) {
        Map<Long, TableRating> rating = getBookedTablesRating(dto.startTime(), dto.endTime());

        List<TableEntity> tables = tableService.findAll();

        for (TableEntity table : tables) {
            long tableId = table.getId();

            TableRating tableRating;
            if (rating.containsKey(tableId)) {
                continue;
            } else if (dto.type() != null && !matchesByType(table.getTypes(), dto.type())) {
                tableRating = TableRating.BAD;
            } else if (Math.abs(table.getGuests() - dto.guests()) >= 2) {
                tableRating = TableRating.BAD;
            } else if (table.getGuests() - dto.guests() != 0) {
                tableRating = TableRating.AVAILABLE;
            } else {
                tableRating = TableRating.PERFECT;
            }

            rating.put(tableId, tableRating);
        }

        return rating;
    }

    private Map<Long, TableRating> getBookedTablesRating(Instant startTime, Instant endTime) {
        return bookingService.findBetween(startTime, endTime).stream()
            .collect(Collectors.toMap(
                BookingEntity::getTableId,
                (ignored) -> TableRating.UNAVAILABLE,
                (current, ignored) -> current
            ));
    }

    private boolean matchesByType(Set<TableTypeEntity> tableTypes, String filterType) {
        for (TableTypeEntity entity : tableTypes) {
            if (entity.getType().equals(filterType)) {
                return true;
            }
        }
        return false;
    }

}
