package exception;

public class AccountAlreadyExistsException extends RuntimeException{

    public AccountAlreadyExistsException() {
        super("Account with taken credentials already exist");
    }
}
