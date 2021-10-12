package exception;

public class WrongCredentialsException extends RuntimeException {

    public WrongCredentialsException() {
        super("Wrong email or(and) password");
    }
}
