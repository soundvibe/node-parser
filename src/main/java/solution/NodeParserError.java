package solution;

/**
 * @author OZY on 2016.11.11.
 */
public class NodeParserError extends RuntimeException {

    private final ErrorType errorType;

    public NodeParserError(ErrorType errorType) {
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        return errorType.toString();
    }
}
