package ee.nimens.restaurant.booking.system.backend.exception.booking;

public class InvalidBookingTimeException extends RuntimeException {

    public InvalidBookingTimeException(String message) {
        super(message);
    }

}
