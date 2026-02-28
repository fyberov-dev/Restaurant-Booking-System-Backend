package ee.nimens.restaurant.booking.system.backend.util.mapper;

import ee.nimens.restaurant.booking.system.backend.dto.table.TableTypeDto;
import ee.nimens.restaurant.booking.system.backend.entity.TableTypeEntity;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TableTypeMapper {

    public TableTypeDto toDto(TableTypeEntity entity) {
        if (entity == null) {
            return null;
        }

        return TableTypeDto.builder()
            .type(entity.getType())
            .title(entity.getTitle())
            .build();
    }

    public Set<TableTypeDto> toDtos(Set<TableTypeEntity> entities) {
        if (entities == null) {
            return Set.of();
        }

        return entities.stream()
            .map(this::toDto)
            .collect(Collectors.toSet());
    }
}
