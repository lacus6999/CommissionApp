package test.helper;

public class TestFailedException extends RuntimeException {

    public String message;

    @Override
    public String getMessage() {
        return message;
    }

    public TestFailedException(String message) {
        this.message = message;
    }
}
