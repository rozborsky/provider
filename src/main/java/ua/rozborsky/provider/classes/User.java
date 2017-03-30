package ua.rozborsky.provider.classes;

import org.springframework.stereotype.Component;

/**
 * Created by roman on 30.03.2017.
 */
@Component
public class User {
    private int id;
    private String name;
    private String secondName;
    private String address;

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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
