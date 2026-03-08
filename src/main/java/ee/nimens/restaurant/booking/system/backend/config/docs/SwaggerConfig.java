package ee.nimens.restaurant.booking.system.backend.config.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String DEFAULT_APPLICATION_NAME = "Table Reservation System";
    private static final String DEFAULT_APPLICATION_VERSION = "1.0";

    @Bean
    OpenAPI openApi() {
        return new OpenAPI().info(
                new Info()
                        .title(DEFAULT_APPLICATION_NAME)
                        .version(DEFAULT_APPLICATION_VERSION)
        );
    }

}
