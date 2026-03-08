package ee.nimens.restaurant.booking.system.backend.config.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openApi() {
        return new OpenAPI().info(new Info().title("Table Reservation System").version("1.0"));
    }

}
