package ee.nimens.restaurant.booking.system.backend.util.mapper;

import ee.nimens.restaurant.booking.system.backend.dto.request.booking.BookingDto;
import ee.nimens.restaurant.booking.system.backend.entity.BookingEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDto toDto(BookingEntity entity) {
        if (entity == null) {
            return null;
        }

        return BookingDto.builder()
            .id(entity.getId())
            .tableId(entity.getTableId())
            .startsAt(entity.getStartsAt())
            .endsAt(entity.getEndsAt())
            .build();
    }

    public List<BookingDto> toDtos(List<BookingEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
            .map(this::toDto)
            .toList();
    }

}
