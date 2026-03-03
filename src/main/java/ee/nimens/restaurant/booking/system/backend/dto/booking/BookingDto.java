package ee.nimens.restaurant.booking.system.backend.dto.booking;

import java.time.Instant;
import lombok.Builder;

@Builder
public record BookingDto(
    long id,
    long tableId,
    Instant startsAt,
    Instant endsAt
) {
}
