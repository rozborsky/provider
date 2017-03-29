package ua.rozborsky.provider.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.rozborsky.provider.interfaces.DAO;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;

/**
 * Created by roman on 29.03.2017.
 */

@Component
public class DAOspring implements DAO{

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    DAOspring() {

    }

    @PostConstruct
    private void inint() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void initDB() {
        dropTables();
        createTables();
        addUser("Clint", "Eastwood", "Los Angeles");
        addUser("Philip Seymour", "Hoffman", "New York, Manhattan");
        addUser("Robert", "de Diro", "New York, Greenwich village");
        addUser("Al", "Pacino", "New York, East Harlem");

        addScore(1, new BigDecimal(100.38));
        addScore(2, new BigDecimal(3060));
        addScore(3, new BigDecimal(0.523));
        addScore(4, new BigDecimal(50.06));

        addRate("Standart", new BigDecimal(10));
        addRate("VIP", new BigDecimal(100));
    }


    private void dropTables() {
        jdbcTemplate.execute(
                "DROP TABLE IF EXISTS users, score, rate"
        );
    }


    public void addUser(String name, String secondName, String address) {
        jdbcTemplate.update(
                "INSERT INTO users (name, second_name, address) VALUES (?, ?, ?)",
                name, secondName, address
        );
    }


    public void addScore(int id_user, BigDecimal money) {
        jdbcTemplate.update(
                "INSERT INTO score (id_user, money) VALUES (?, ?)",
                id_user, money
        );
    }


    public void addRate(String name, BigDecimal score) {
        jdbcTemplate.update(
                "INSERT INTO rate (name, cost) VALUES (?, ?)",
                name, score
        );
    }


    private void createTables() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS public.users\n" +
                "(\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "name character varying(20),\n" +
                "second_name character varying(30),\n" +
                "address character varying(100)\n" +
                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS public.score\n" +
                "(\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "id_user integer,\n" +
                "money NUMERIC(10, 5)\n" +
                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS public.rate\n" +
                "(\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "name character varying(20),\n" +
                "cost NUMERIC(10, 5)\n" +
                ")");
    }
}

