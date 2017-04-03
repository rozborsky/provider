package ua.rozborsky.provider.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.rozborsky.provider.interfaces.DAO;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;

/**
 * Created by roman on 01.04.2017.
 */
@Component
public class InitDB {
    @Autowired
    DAO dao;

    @Autowired
    DataSource dataSource;

    @Autowired
    Time time;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void initDB() {
        dropTables();
        createTables();
        dao.addUser("Clint", "Eastwood", "Los Angeles", 1451952000000L);
        dao.addUser("Philip Seymour", "Hoffman", "New York, Manhattan", 1451520000000L);
        dao.addUser("Robert", "de Diro", "New York, Greenwich village", 1460764800000L);
        dao.addUser("Al", "Pacino", "New York, East Harlem", 1451520000000L);

        dao.addScore(1, 1, new BigDecimal(100.38));
        dao.addScore(2, 1, new BigDecimal(3060));
        dao.addScore(3, 2, new BigDecimal(0.523));
        dao.addScore(4, 1, new BigDecimal(50.06));

        dao.addRate("Standart", new BigDecimal(10));
        dao.addRate("VIP", new BigDecimal(100));

        dao.addTransaction(1, time.getTimestamp(2016, 1, 5, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(1, time.getTimestamp(2016, 1, 6, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 1, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 1, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(4, time.getTimestamp(2016, 1, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(4, time.getTimestamp(2016, 1, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(1, time.getTimestamp(2016, 2, 4, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(1, time.getTimestamp(2016, 2, 6, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 2, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 2, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(4, time.getTimestamp(2016, 2, 25, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(4, time.getTimestamp(2016, 2, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(1, time.getTimestamp(2016, 3, 5, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(1, time.getTimestamp(2016, 3, 6, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 2, 28, 12, 0, 0 ),
                new BigDecimal(20));
        dao.addTransaction(2, time.getTimestamp(2016, 3, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(4, time.getTimestamp(2016, 3, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(4, time.getTimestamp(2016, 3, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(1, time.getTimestamp(2016, 4, 7, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(1, time.getTimestamp(2016, 4, 8, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 4, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 4, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(3, time.getTimestamp(2016, 4, 17, 12, 0, 0 ),
                new BigDecimal(100));
        dao.addTransaction(3, time.getTimestamp(2016, 4, 18, 03, 0, 0 ),
                new BigDecimal(-100));
        dao.addTransaction(4, time.getTimestamp(2016, 4, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(4, time.getTimestamp(2016, 4, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(1, time.getTimestamp(2016, 5, 4, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(1, time.getTimestamp(2016, 5, 6, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 5, 2, 12, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(4, time.getTimestamp(2016, 5, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(4, time.getTimestamp(2016, 5, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(1, time.getTimestamp(2016, 5, 5, 12, 0, 0 ),
                new BigDecimal(100));
        dao.addTransaction(1, time.getTimestamp(2016, 5, 6, 3, 0, 0 ),
                new BigDecimal(-100));
        dao.addTransaction(2, time.getTimestamp(2016, 5, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 5, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 6, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 6, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 7, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 7, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 8, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 8, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 9, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 9, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 10, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 10, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 11, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 11, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        dao.addTransaction(2, time.getTimestamp(2016, 12, 1, 12, 0, 0 ),
                new BigDecimal(10));
        dao.addTransaction(2, time.getTimestamp(2016, 12, 2, 3, 0, 0 ),
                new BigDecimal(-10));
    }

    private void dropTables() {
        jdbcTemplate.execute(
                "DROP TABLE IF EXISTS users, score, rate, transactions"
        );
    }


    private void createTables() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS public.users\n" +
                "(\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "name character varying(20),\n" +
                "second_name character varying(30),\n" +
                "address character varying(100),\n" +
                "registrationDate bigint\n" +
                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS public.score\n" +
                "(\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "id_user integer,\n" +
                "id_rate integer,\n" +
                "money NUMERIC(10, 5)\n" +
                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS public.rate\n" +
                "(\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "name character varying(20),\n" +
                "cost NUMERIC(10, 5)\n" +
                ")");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS public.transactions\n" +
                "(\n" +
                "id SERIAL PRIMARY KEY,\n" +
                "id_user integer,\n" +
                "timestamp bigint,\n" +
                "change NUMERIC(10, 5)\n" +
                ")");
    }
}
