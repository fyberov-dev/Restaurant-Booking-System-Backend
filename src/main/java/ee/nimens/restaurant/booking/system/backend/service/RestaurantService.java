package ee.nimens.restaurant.booking.system.backend.service;

import ee.nimens.restaurant.booking.system.backend.dto.restaurant.RestaurantDto;
import ee.nimens.restaurant.booking.system.backend.entity.RestaurantEntity;
import ee.nimens.restaurant.booking.system.backend.repository.RestaurantRepository;
import ee.nimens.restaurant.booking.system.backend.util.DateUtil;
import ee.nimens.restaurant.booking.system.backend.util.mapper.RestaurantMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private static final long RESTAURANT_ID = 1;

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    /**
     * Get restaurant metadata.
     *
     * @return metadata of a restaurant
     */
    public RestaurantDto get(Instant date) {
        RestaurantEntity entity = restaurantRepository.findById(RESTAURANT_ID)
            .orElse(null);

        List<Instant> availableTimings = getTimings(entity, date);

        return restaurantMapper.toDto(entity, availableTimings);
    }

    private List<Instant> getTimings(RestaurantEntity entity, Instant date) {
        Instant openingTime = DateUtil.atTime(date, entity.getOpenTime());
        Instant closingTime = DateUtil.atTime(date, entity.getCloseTime());

        List<Instant> times = new ArrayList<>();

        Instant current = openingTime;
        while (closingTime.isAfter(current)) {
            times.add(current);
            current = current.plus(entity.getTimingsStep(), ChronoUnit.MINUTES);
        }

        return times;
    }

}
