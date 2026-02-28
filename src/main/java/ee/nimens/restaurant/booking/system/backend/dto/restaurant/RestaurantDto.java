package ee.nimens.restaurant.booking.system.backend.dto.restaurant;

import java.time.Instant;
import java.time.LocalTime;
import lombok.Builder;

@Builder
public record RestaurantDto(
    LocalTime openTime,
    LocalTime closeTime,
    int timingsStep,
    int maxBookHours,
    Instant createdAt,
    Instant updatedAt
) {
}
