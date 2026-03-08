package ee.nimens.restaurant.booking.system.backend.dto.restaurant;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import lombok.Builder;

@Builder
public record RestaurantDto(
    LocalTime openTime,
    LocalTime closeTime,
    int timingsStep,
    int maxBookHours,
    List<Instant> availableTimings,
    String zoneId,
    Instant createdAt,
    Instant updatedAt
) {
}
