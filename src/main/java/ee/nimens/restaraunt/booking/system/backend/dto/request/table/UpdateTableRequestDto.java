package ee.nimens.restaraunt.booking.system.backend.dto.request.table;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record UpdateTableRequestDto(

    @Schema(requiredMode = NOT_REQUIRED)
    @Min(value = 1, message = "Guest value should be atleast 1")
    Integer guests,

    @Schema(requiredMode = NOT_REQUIRED)
    @Min(value = 0, message = "X value should be atleast 0")
    Float x,

    @Schema(requiredMode = NOT_REQUIRED)
    @Min(value = 0, message = "Y value should be atleast 0")
    Float y
) {
}
