package ua.rozborsky.provider.interfaces;

import ua.rozborsky.provider.classes.Rate;
import ua.rozborsky.provider.classes.Score;
import ua.rozborsky.provider.classes.Transaction;
import ua.rozborsky.provider.classes.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by roman on 29.03.2017.
 */
public interface DAO {
    int addUser(String name, String secondName, String address);
    void addScore(int id_user, int id_rate, BigDecimal money);
    void addRate(String name, BigDecimal score);
    void addTransaction(int id_user, long timestamp, BigDecimal change);

    List<User> getUsers();
    User getUser(int id);
    Score getScore(int idUser);
    Rate getRate(int id);
    List<Rate> rateList();
    void updateRate(int idRate, int id);
    List<Transaction> getTransactions();
    List<Transaction> getTransactions(String name, String surname, long startDate, long finishDate);
}
