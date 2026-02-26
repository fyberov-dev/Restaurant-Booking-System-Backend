package ee.nimens.restaurant.booking.system.backend.dto.request.booking;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record BookingDto(
    long id,
    long tableId,
    ZonedDateTime startsAt,
    ZonedDateTime endsAt
) {
}
