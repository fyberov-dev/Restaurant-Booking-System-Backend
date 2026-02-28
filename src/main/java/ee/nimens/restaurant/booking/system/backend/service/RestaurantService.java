package ee.nimens.restaurant.booking.system.backend.service;

import ee.nimens.restaurant.booking.system.backend.dto.restaurant.RestaurantDto;
import ee.nimens.restaurant.booking.system.backend.entity.RestaurantEntity;
import ee.nimens.restaurant.booking.system.backend.repository.RestaurantRepository;
import ee.nimens.restaurant.booking.system.backend.util.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private static final long RESTAURANT_ID = 1;

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantDto get() {
        RestaurantEntity entity = restaurantRepository.findById(RESTAURANT_ID)
            .orElse(null);

        return restaurantMapper.toDto(entity);
    }

}
