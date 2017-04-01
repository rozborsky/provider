package ua.rozborsky.provider.classes;

import java.math.BigDecimal;

/**
 * Created by roman on 01.04.2017.
 */
public class Transaction {
    private int id;
    private int idUser;
    private long timestamp;
    private BigDecimal change;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }
}
