package ee.nimens.restaurant.booking.system.backend.dto.table;

import java.time.Instant;
import lombok.Builder;

@Builder
public record TableDto(
    Long id,
    int guests,
    double x,
    double y,
    Instant createdAt,
    Instant updatedAt
) {
}
