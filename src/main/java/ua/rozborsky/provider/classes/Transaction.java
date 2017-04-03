package ua.rozborsky.provider.classes;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by roman on 01.04.2017.
 */
@Component
public class Transaction {
    private int id;
    private int idUser;
    private String date;
    private String name;
    private String surname;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }
}
