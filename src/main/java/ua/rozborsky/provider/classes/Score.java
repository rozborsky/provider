package ua.rozborsky.provider.classes;

import java.math.BigDecimal;

/**
 * Created by roman on 30.03.2017.
 */
public class Score {
    private int id;
    private int idUser;
    private BigDecimal money;
    private int idRate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

}
