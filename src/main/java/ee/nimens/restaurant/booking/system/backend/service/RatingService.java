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

    /**
     * Get rating of the tables by filters.
     * <p></p>
     * Available filters:
     *  - Start time
     *  - End time
     *  - Guests number
     *  - Table type
     * <p></p>
     * System works like: less rating -> better match
     * <p></p>
     * Rating distribution:
     *  - 0 -> PERFECT MATCH
     *  - 1 -> GOOD MATCH
     *  - >1 -> BAD MATCH
     *  - Already Booked -> UNAVAILABLE
     * <p></p>
     * If table type provided and table's type doesn't equal, then rating will be +1
     * If table has more than 2 needed sits, then rating will be +2
     * If table has from 1 to 2 more needed sits, then rating will be +1
     *
     * @param dto table filters
     * @return map where key={id of the table} and value={table rating}
     */
    public Map<Long, TableRating> getRating(FilterBookingDto dto) {
        Map<Long, TableRating> rating = getBookedTablesRating(dto.startTime(), dto.endTime());

        List<TableEntity> tables = tableService.findAll();

        for (TableEntity table : tables) {
            long tableId = table.getId();

            if (rating.containsKey(tableId)) {
                continue;
            }
            
            int currentRating = 0;
            if (dto.type() != null && !matchesByType(table.getTypes(), dto.type())) {
                currentRating += 1;
            }

            int guestsDiff = Math.abs(table.getGuests() - dto.guests());
            if (guestsDiff > 2) {
                currentRating += 2;
            } else if (guestsDiff >= 1) {
                currentRating += 1;
            }
            
            TableRating tableRating = switch (currentRating) {
                case 0 -> TableRating.PERFECT;
                case 1 -> TableRating.AVAILABLE;
                default -> TableRating.BAD;
            };

            rating.put(tableId, tableRating);
        }

        return rating;
    }

    /**
     * Get all bookings between start and end time and make them UNAVAILABLE (Because they are busy).
     *
     * @param start bookings should be before this Instant
     * @param end bookings should be after this Instant
     * @return map of bookings rated as UNAVAILABLE
     */
    private Map<Long, TableRating> getBookedTablesRating(Instant start, Instant end) {
        return bookingService.findBetween(start, end).stream()
            .collect(Collectors.toMap(
                BookingEntity::getTableId,
                (ignored) -> TableRating.UNAVAILABLE,
                (current, ignored) -> current
            ));
    }

    /**
     * Check if table matches by type.
     * <p></p>
     * Table can have many types, so it's types should be iterated and checked if any of them matches needed type.
     *
     * @param tableTypes types of the table
     * @param filterType type to match
     * @return true if table has needed type, otherwise return false
     */
    private boolean matchesByType(Set<TableTypeEntity> tableTypes, String filterType) {
        for (TableTypeEntity entity : tableTypes) {
            if (entity.getType().equals(filterType)) {
                return true;
            }
        }
        return false;
    }

}
