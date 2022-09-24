package tablou.parser;

public class FailedToParseException extends Exception {
    String message;
    FailedToParseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
