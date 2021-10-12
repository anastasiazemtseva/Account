import entity.Account;
import manager.AccountManager;
import manager.FileAccountManager;

import java.io.IOException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws IOException {
        Account account = new Account("jorj", "psadsa", "abadsasd", LocalDate.of(2002, 02, 20), "sweqwes@dsd.tu", "s3wqeqwtt", false);
        AccountManager accountManager = new FileAccountManager();
        accountManager.register(account);
    }
}
