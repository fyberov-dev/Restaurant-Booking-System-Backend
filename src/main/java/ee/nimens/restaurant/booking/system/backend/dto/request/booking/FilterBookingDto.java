package ee.nimens.restaurant.booking.system.backend.dto.request.booking;

import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public record FilterBookingDto(
    @NotNull(message = "startTime param should not be null")
    ZonedDateTime startTime,

    @NotNull(message = "endTime param should not be null")
    ZonedDateTime endTime,

    @NotNull(message = "guests param should not be null")
    int guests
) {
}
