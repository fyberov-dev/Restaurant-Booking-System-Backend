package ee.nimens.restaurant.booking.system.backend.controller;

import ee.nimens.restaurant.booking.system.backend.dto.restaurant.RestaurantDto;
import ee.nimens.restaurant.booking.system.backend.service.RestaurantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Restaurant", description = "Get restaurant data")
@RestController
@RequestMapping("/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<RestaurantDto> get() {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.get());
    }

}
