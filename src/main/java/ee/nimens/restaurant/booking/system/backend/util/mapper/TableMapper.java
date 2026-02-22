package ee.nimens.restaurant.booking.system.backend.util.mapper;

import ee.nimens.restaurant.booking.system.backend.dto.table.TableDto;
import ee.nimens.restaurant.booking.system.backend.entity.TableEntity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TableMapper {

    public TableDto toDto(TableEntity entity) {
        if (entity == null) {
            return null;
        }

        return TableDto.builder()
            .id(entity.getId())
            .guests(entity.getGuests())
            .x(entity.getPosX())
            .y(entity.getPosY())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();
    }

    public List<TableDto> toDtos(List<TableEntity> entities) {
        if (entities == null) {
            return List.of();
        }

        return entities.stream()
            .map(this::toDto)
            .toList();
    }

}
