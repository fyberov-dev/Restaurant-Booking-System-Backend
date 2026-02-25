package ee.nimens.restaurant.booking.system.backend.dto.request.booking;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BookingDto(
    long id,
    long tableId,
    LocalDateTime startsAt,
    LocalDateTime endsAt
) {
}
