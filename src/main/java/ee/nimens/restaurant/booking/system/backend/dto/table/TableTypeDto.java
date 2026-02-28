package ee.nimens.restaurant.booking.system.backend.dto.table;

import lombok.Builder;

@Builder
public record TableTypeDto(
    String type,
    String title
) {
}
