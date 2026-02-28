package ee.nimens.restaurant.booking.system.backend.dto.table;

import java.time.Instant;
import java.util.Set;
import lombok.Builder;

@Builder
public record TableDto(
    Long id,
    int guests,
    double x,
    double y,
    boolean isVertical,
    Set<TableTypeDto> types,
    Instant createdAt,
    Instant updatedAt
) {
}
