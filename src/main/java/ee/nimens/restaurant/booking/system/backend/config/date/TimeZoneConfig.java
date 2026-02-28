package ee.nimens.restaurant.booking.system.backend.config.date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.application")
@Getter
@Setter
public class TimeZoneConfig {

    private String timezone;

}
