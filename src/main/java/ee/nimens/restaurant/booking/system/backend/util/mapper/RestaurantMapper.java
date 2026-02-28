package ee.nimens.restaurant.booking.system.backend.util.mapper;

import ee.nimens.restaurant.booking.system.backend.dto.restaurant.RestaurantDto;
import ee.nimens.restaurant.booking.system.backend.entity.RestaurantEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantDto toDto(RestaurantEntity entity) {
        if (entity == null) {
            return null;
        }

        return RestaurantDto.builder()
            .openTime(entity.getOpenTime())
            .closeTime(entity.getCloseTime())
            .timingsStep(entity.getTimingsStep())
            .maxBookHours(entity.getMaxBookHours())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();
    }

}
