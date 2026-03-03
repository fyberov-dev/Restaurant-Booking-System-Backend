package ee.nimens.restaurant.booking.system.backend.dto.request.booking;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record FilterBookingDto(
    @NotNull(message = "startTime param should not be null")
    Instant startTime,

    @NotNull(message = "endTime param should not be null")
    Instant endTime,

    @NotNull(message = "guests param should not be null")
    int guests,

    String type
) {
}
