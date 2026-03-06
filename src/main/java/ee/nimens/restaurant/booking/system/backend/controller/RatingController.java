package ee.nimens.restaurant.booking.system.backend.controller;

import ee.nimens.restaurant.booking.system.backend.dto.booking.TableRating;
import ee.nimens.restaurant.booking.system.backend.dto.request.booking.FilterBookingDto;
import ee.nimens.restaurant.booking.system.backend.service.RatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Table Rating", description = "Get the most suitable table for customers")
@RestController
@RequestMapping("/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping
    public ResponseEntity<Map<Long, TableRating>> getBookingsBetween(@Valid FilterBookingDto dto) {
        return ResponseEntity.ok().body(ratingService.getRating(dto));
    }

}
