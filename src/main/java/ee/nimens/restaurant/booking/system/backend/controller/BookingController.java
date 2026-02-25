package ee.nimens.restaurant.booking.system.backend.controller;

import ee.nimens.restaurant.booking.system.backend.dto.request.booking.BookingDto;
import ee.nimens.restaurant.booking.system.backend.dto.request.booking.CreateBookingRequestDto;
import ee.nimens.restaurant.booking.system.backend.service.BookingService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDto>> get(
        @RequestParam LocalDateTime startTime,
        @RequestParam LocalDateTime endTime
    ) {
        return ResponseEntity.ok().body(bookingService.get(startTime, endTime));
    }

    @PostMapping
    public ResponseEntity<BookingDto> create(@RequestBody @Valid CreateBookingRequestDto dto) {
        return ResponseEntity.ok().body(bookingService.create(dto));
    }

}
