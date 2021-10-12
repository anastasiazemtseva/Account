package manager;

import entity.Account;

import java.io.IOException;
//описание поведения всех реализаций данного интерфейса
public interface AccountManager {

    void register(Account account) throws IOException;
    Account login(String email, String password) throws IOException;
    void removeAccount(String email, String password) throws IOException;
}
