package manager;

import entity.Account;
import exception.AccountAlreadyExistsException;
import exception.AccountBlockedException;
import exception.WrongCredentialsException;
import persistence.FileService;

import java.io.IOException;
import java.util.List;

public class FileAccountManager implements AccountManager{

    @Override
    public void register(Account account) throws IOException {
        List<Account> accountList = FileService.getInstance().read();

        for (Account a: accountList) {
            if (account.getEmail().equals(a.getEmail())) {
                throw new AccountAlreadyExistsException();
            }
        }
        accountList.add(account);
        FileService.getInstance().write(accountList);
    }

    @Override
    public Account login(String email, String password) throws IOException {
        List<Account> accountList = FileService.getInstance().read();
        for (Account account: accountList) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                if (!account.isBlocked()) {
                    FailedLoginCounter.getInstance().successLogin(email);
                    return account;
                }
                throw new AccountBlockedException();
            }
        }
        if (FailedLoginCounter.getInstance().failedLogin(email) == 5) {
            for (Account account: accountList) {
                if (account.getEmail().equals(email)) {
                    //блок аккаунт
                    account.setBlocked(true);
                }
            }
        }
        throw new WrongCredentialsException();
    }

    @Override
    public void removeAccount(String email, String password) throws IOException {
        List<Account> accountList = FileService.getInstance().read();
        //инициализируем объект
        Account accountToBlock = null;
        for (Account account: accountList) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                //сохраняем чтобы удалить потом
                accountToBlock = account;
                break;
            }
        }
        //что мы нашли
        if (accountToBlock != null) {
            accountList.remove(accountToBlock);
            //записываем все акк в файл
            FileService.getInstance().write(accountList);
        } else {
            throw new WrongCredentialsException();
        }
    }
}
