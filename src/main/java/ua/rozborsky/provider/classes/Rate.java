package ua.rozborsky.provider.classes;

import java.math.BigDecimal;

/**
 * Created by roman on 31.03.2017.
 */
public class Rate {
    private int id;
    private String name;
    private BigDecimal cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
