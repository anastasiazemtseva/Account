package manager;

import java.util.HashMap;
import java.util.Map;

public class FailedLoginCounter {

    private static FailedLoginCounter instance;
    private Map<String, Integer> failedMap = new HashMap<>();
//singleton
    private FailedLoginCounter() {}

    public static FailedLoginCounter getInstance() {
        if (instance == null) {
            instance = new FailedLoginCounter();
        }
        return instance;
    }
//
    public int failedLogin(String email) {
        //не содержит
        if (!failedMap.containsKey(email)) {
            failedMap.put(email, 1);
        } else {
            failedMap.put(email, failedMap.get(email) + 1);
        }
        //возвр колво неудачных попыток
        return failedMap.get(email);
    }
    //Обнуляем попытки
    public void successLogin(String email) {
        failedMap.put(email, 0);
    }
}
