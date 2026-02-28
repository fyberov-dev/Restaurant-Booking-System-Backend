package ee.nimens.restaurant.booking.system.backend.service;

import ee.nimens.restaurant.booking.system.backend.dto.request.booking.FilterBookingDto;
import ee.nimens.restaurant.booking.system.backend.dto.request.booking.TableRating;
import ee.nimens.restaurant.booking.system.backend.entity.BookingEntity;
import ee.nimens.restaurant.booking.system.backend.entity.TableEntity;
import java.time.ZonedDateTime;
import java.util.HashMap;
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

    private static final int DEFAULT_PERFECT_TABLE_RATING = 0;
    private static final int DEFAULT_GOOD_TABLE_RATING = 1;
    private static final int DEFAULT_UNAVAILABLE_TABLE_RATING = 999;

    public Map<Long, TableRating> getRating(FilterBookingDto dto) {
        Set<Long> bookedTablesIds = getBookedTablesIds(dto.startTime(), dto.endTime());

        Map<Long, Integer> rating = new HashMap<>();

        fillRatingWithUnavailableTables(rating, bookedTablesIds);

        List<TableEntity> tables = tableService.findAll();
        for (TableEntity table : tables) {
            if (rating.containsKey(table.getId())) {
                continue;
            }

            int currentRating = 0;

            currentRating += countGuestsRating(table.getGuests(), dto.guests());

            rating.put(table.getId(), currentRating);
        }

        return distributeTablesByRating(rating);
    }

    private Map<Long, TableRating> distributeTablesByRating(Map<Long, Integer> rating) {
        Map<Long, TableRating> distributedTables = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : rating.entrySet()) {
            Long id = entry.getKey();
            Integer currentRating = entry.getValue();

            TableRating currentTableRating = determineRating(currentRating);

            distributedTables.put(id, currentTableRating);
        }

        return distributedTables;
    }

    private TableRating determineRating(Integer rating) {
        if (rating.equals(DEFAULT_PERFECT_TABLE_RATING)) {
            return TableRating.PERFECT;
        } else if (rating.equals(DEFAULT_GOOD_TABLE_RATING)) {
            return TableRating.GOOD;
        } else if (rating.equals(DEFAULT_UNAVAILABLE_TABLE_RATING)) {
            return TableRating.UNAVAILABLE;
        } else {
            return TableRating.BAD;
        }
    }

    private int countGuestsRating(int tableGuests, int filterGuests) {
        int guestsDiff = Math.abs(tableGuests - filterGuests);

        if (guestsDiff > 2) {
            return 2;
        }

        return Math.abs(guestsDiff);
    }

    private void fillRatingWithUnavailableTables(Map<Long, Integer> rating, Set<Long> unavailableTablesIds) {
        for (long id : unavailableTablesIds) {
            rating.put(id, DEFAULT_UNAVAILABLE_TABLE_RATING);
        }
    }

    private Set<Long> getBookedTablesIds(ZonedDateTime startTime, ZonedDateTime endTime) {
        return bookingService.findBetween(startTime, endTime).stream()
            .map(BookingEntity::getTableId)
            .collect(Collectors.toSet());
    }

}
