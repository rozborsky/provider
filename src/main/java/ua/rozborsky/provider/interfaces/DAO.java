package ua.rozborsky.provider.interfaces;

import java.math.BigDecimal;

/**
 * Created by roman on 29.03.2017.
 */
public interface DAO {
    void initDB();
    void addUser(String name, String secondName, String address);
    void addScore(int id_user, BigDecimal money);
    void addRate(String name, BigDecimal score);
}
