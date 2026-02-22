package ee.nimens.restaraunt.booking.system.backend.exception.table;

public class TableNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Table not found";

    public TableNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public TableNotFoundException(String message) {
        super(message);
    }

}
