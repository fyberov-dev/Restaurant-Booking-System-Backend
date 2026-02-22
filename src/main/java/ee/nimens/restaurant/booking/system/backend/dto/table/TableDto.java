package ee.nimens.restaurant.booking.system.backend.dto.table;

import java.time.Instant;
import lombok.Builder;

@Builder
public record TableDto(
    Long id,
    int guests,
    float x,
    float y,
    Instant createdAt,
    Instant updatedAt
) {
}
