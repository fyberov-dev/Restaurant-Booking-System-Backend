package ee.nimens.restaurant.booking.system.backend.exception;

import ee.nimens.restaurant.booking.system.backend.exception.booking.InvalidBookingTimeException;
import ee.nimens.restaurant.booking.system.backend.exception.table.TableNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(
        HttpServletRequest req,
        MethodArgumentNotValidException ex
    ) {
        List<String> errors = ex.getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .toList();

        return handleBadRequest(req, String.join(",", errors));
    }

    @ExceptionHandler(TableNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleTableNotFoundException(
        HttpServletRequest req,
        TableNotFoundException ex
    ) {
        return handleNotFoundRequest(req, ex.getMessage());
    }

    @ExceptionHandler(InvalidBookingTimeException.class)
    public ResponseEntity<ProblemDetail> handleInvalidBookingTimeException(
        HttpServletRequest req,
        InvalidBookingTimeException ex
    ) {
        return handleBadRequest(req, ex.getMessage());
    }

    public ResponseEntity<ProblemDetail> handleNotFoundRequest(HttpServletRequest req, String message) {
        return handleErrorResponse(req, HttpStatus.NOT_FOUND, message);
    }

    public ResponseEntity<ProblemDetail> handleBadRequest(HttpServletRequest req, String message) {
        return handleErrorResponse(req, HttpStatus.BAD_REQUEST, message);
    }

    public ResponseEntity<ProblemDetail> handleErrorResponse(HttpServletRequest req,
                                                             HttpStatus status,
                                                             String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setInstance(URI.create(req.getRequestURI()));
        problemDetail.setProperty("timestamp", Instant.now());

        return ResponseEntity.status(status).body(problemDetail);
    }

}
