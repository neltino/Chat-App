package CustomExceptions;

public class UnableToConnectToServer extends Exception{
    public UnableToConnectToServer(String message) {
        super(message);
    }
}
