package ee.nimens.restaurant.booking.system.backend;

import ee.nimens.restaurant.booking.system.backend.config.date.TimeZoneConfig;
import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BackendApplication {

    private final TimeZoneConfig timeZoneConfig;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZoneConfig.getTimezone()));
    }
}
