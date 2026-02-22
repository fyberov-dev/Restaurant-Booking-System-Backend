package ee.nimens.restaraunt.booking.system.backend.dto.table;

import java.time.Instant;
import lombok.Builder;

@Builder
public record TableDto(
    long id,
    int guests,
    float x,
    float y,
    Instant createdAt,
    Instant updatedAt
) {
}
