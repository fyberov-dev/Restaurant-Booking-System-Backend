package ee.nimens.restaurant.booking.system.backend.dto.request.booking;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;

public record CreateBookingRequestDto(
    @Schema(requiredMode = REQUIRED)
    Long tableId,

    @Schema(requiredMode = REQUIRED)
    @Size(min = 8, message = "Phone should be atleast 8 characters")
    String phone,

    @Schema(requiredMode = REQUIRED)
    @Email(message = "Invalid email format. Correct email format: example@gmail.com")
    String email,

    @Schema(requiredMode = REQUIRED)
    ZonedDateTime startsAt,

    @Schema(requiredMode = REQUIRED)
    ZonedDateTime endsAt
) {
}
