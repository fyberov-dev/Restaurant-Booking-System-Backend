package ee.nimens.restaurant.booking.system.backend.service;

import ee.nimens.restaurant.booking.system.backend.entity.RestaurantEntity;
import ee.nimens.restaurant.booking.system.backend.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private static final long RESTAURANT_ID = 1;

    private final RestaurantRepository restaurantRepository;

    public RestaurantEntity get() {
        return restaurantRepository.findById(RESTAURANT_ID)
            .orElse(null);
    }

}
