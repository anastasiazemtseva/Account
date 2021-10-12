package exception;

public class AccountBlockedException extends RuntimeException {

    public AccountBlockedException() {
        super("Account with taken credentials is blocked");
    }
}
