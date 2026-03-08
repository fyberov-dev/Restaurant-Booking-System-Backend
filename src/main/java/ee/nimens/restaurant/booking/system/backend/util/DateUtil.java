package ee.nimens.restaurant.booking.system.backend.util;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtil {

    /**
     * Format date. Truncate it by minutes, so seconds/milliseconds will be always 0.
     * Add a TimeZone the application is working at.
     *
     * @param date date to format
     * @return formatted date
     */
    public Instant formatDate(Instant date) {
        return format(date).toInstant();
    }

    public Instant atTime(Instant date, LocalTime time) {
        return format(date)
                .toLocalDate()
                .atTime(time)
                .atZone(TimeZone.getDefault().toZoneId())
                .toInstant();

    }

    private ZonedDateTime format(Instant date) {
        return date.truncatedTo(ChronoUnit.MINUTES)
                .atZone(TimeZone.getDefault().toZoneId());
    }

}
