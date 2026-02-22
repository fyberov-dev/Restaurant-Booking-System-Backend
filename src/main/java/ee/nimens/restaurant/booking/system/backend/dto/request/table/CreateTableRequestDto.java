package ee.nimens.restaurant.booking.system.backend.dto.request.table;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record CreateTableRequestDto(

    @Schema(requiredMode = REQUIRED)
    @Min(value = 1, message = "Guest value should be atleast 1")
    int guests,

    @Schema(requiredMode = REQUIRED)
    @Min(value = 0, message = "X value should be atleast 0")
    double x,

    @Schema(requiredMode = REQUIRED)
    @Min(value = 0, message = "Y value should be atleast 0")
    double y,

    @Schema(requiredMode = NOT_REQUIRED, defaultValue = "false")
    Boolean isVertical
) {
}
