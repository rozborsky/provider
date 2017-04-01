package ua.rozborsky.provider.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import ua.rozborsky.provider.interfaces.DAO;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

/**
 * Created by roman on 29.03.2017.
 */

@Component
public class DAOspring implements DAO{

    @Autowired
    DataSource dataSource;

    @Autowired
    Time time;

    private JdbcTemplate jdbcTemplate;

    DAOspring() {

    }

    @PostConstruct
    private void inint() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<User> getUsers() {
        return jdbcTemplate.query(
                "SELECT * FROM users",
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        user.setSecondName(rs.getString("second_name"));
                        user.setAddress(rs.getString("address"));
                        return user;
                    }
                });
    }

    public User getUser(int id){
        User user = jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE id = ?",
                new Object[]{id},
                new RowMapper<User>() {
                    public  User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        user.setSecondName(rs.getString("second_name"));
                        user.setAddress(rs.getString("address"));
                        return user;
                    }
                } );
        return user;
    }

    public Score getScore(int idUser){
        Score score = jdbcTemplate.queryForObject(
                "SELECT * FROM score WHERE id_user = ?",
                new Object[]{idUser},
                new RowMapper<Score>() {
                    public  Score mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Score score = new Score();
                        score.setId(rs.getInt("id"));
                        score.setIdUser(rs.getInt("id_user"));
                        score.setIdRate(rs.getInt("id_rate"));
                        score.setMoney(new BigDecimal(rs.getString("money")));
                        return score;
                    }
                } );
        return score;
    }

    public Rate getRate(int id){
        Rate rate = jdbcTemplate.queryForObject(
                "SELECT * FROM rate WHERE id = ?",
                new Object[]{id},
                new RowMapper<Rate>() {
                    public  Rate mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Rate rate = new Rate();
                        rate.setId(rs.getInt("id"));
                        rate.setName(rs.getString("name"));
                        rate.setCost(new BigDecimal(rs.getString("cost")));
                        return rate;
                    }
                } );
        return rate;
    }

    public List<Rate> rateList() {
        return jdbcTemplate.query(
                "SELECT * FROM rate",
                new RowMapper<Rate>() {
                    public Rate mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Rate rate = new Rate();
                        rate.setId(rs.getInt("id"));
                        rate.setName(rs.getString("name"));
                        rate.setCost(new BigDecimal(rs.getString("cost")));
                        return rate;
                    }
                });
    }

    public void updateRate(int idRate, int id) {
        jdbcTemplate.update("UPDATE score SET id_rate=? WHERE id=?",
                idRate, id);
    }

    public void initDB() {
        dropTables();
        createTables();
        addUser("Clint", "Eastwood", "Los Angeles");
        addUser("Philip Seymour", "Hoffman", "New York, Manhattan");
        addUser("Robert", "de Diro", "New York, Greenwich village");
        addUser("Al", "Pacino", "New York, East Harlem");

        addScore(1, 1, new BigDecimal(100.38));
        addScore(2, 1, new BigDecimal(3060));
        addScore(3, 2, new BigDecimal(0.523));
        addScore(4, 1, new BigDecimal(50.06));

        addRate("Standart", new BigDecimal(10));
        addRate("VIP", new BigDecimal(100));

        addTransaction(1, time.getTimestamp(2016, 1, 5, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(1, time.getTimestamp(2016, 1, 6, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 1, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 1, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(4, time.getTimestamp(2016, 1, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(4, time.getTimestamp(2016, 1, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(1, time.getTimestamp(2016, 2, 4, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(1, time.getTimestamp(2016, 2, 6, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 2, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 2, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(4, time.getTimestamp(2016, 2, 25, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(4, time.getTimestamp(2016, 2, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(1, time.getTimestamp(2016, 3, 5, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(1, time.getTimestamp(2016, 3, 6, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 2, 28, 12, 0, 0 ),
                new BigDecimal(20));
        addTransaction(2, time.getTimestamp(2016, 3, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(4, time.getTimestamp(2016, 3, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(4, time.getTimestamp(2016, 3, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(1, time.getTimestamp(2016, 4, 7, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(1, time.getTimestamp(2016, 4, 8, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 4, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 4, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(3, time.getTimestamp(2016, 4, 17, 12, 0, 0 ),
                new BigDecimal(100));
        addTransaction(3, time.getTimestamp(2016, 4, 18, 03, 0, 0 ),
                new BigDecimal(-100));
        addTransaction(4, time.getTimestamp(2016, 4, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(4, time.getTimestamp(2016, 4, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(1, time.getTimestamp(2016, 5, 4, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(1, time.getTimestamp(2016, 5, 6, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 5, 2, 12, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(4, time.getTimestamp(2016, 5, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(4, time.getTimestamp(2016, 5, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(1, time.getTimestamp(2016, 5, 5, 12, 0, 0 ),
                new BigDecimal(100));
        addTransaction(1, time.getTimestamp(2016, 5, 6, 3, 0, 0 ),
                new BigDecimal(-100));
        addTransaction(2, time.getTimestamp(2016, 5, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 5, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 6, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 6, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 7, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 7, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 8, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 8, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 9, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 9, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 10, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 10, 2, 3, 0, 0 ),
                new BigDecimal(-10));
        addTransaction(2, time.getTimestamp(2016, 11, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 11, 2, 3, 0, 0 ),
                new BigDecimal(-10));
         addTransaction(2, time.getTimestamp(2016, 12, 1, 12, 0, 0 ),
                new BigDecimal(10));
        addTransaction(2, time.getTimestamp(2016, 12, 2, 3, 0, 0 ),
                new BigDecimal(-10));
    }


    private void dropTables() {
        jdbcTemplate.execute(
                "DROP TABLE IF EXISTS users, score, rate, transactions"
        );
    }


    public int addUser(final String name, final String secondName, final String address) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement("INSERT INTO users (name, second_name, address) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, name);
                statement.setString(2, secondName);
                statement.setString(3, address);
                return statement;
            }
        }, holder);

        return (int)holder.getKeys().get("id");
    }


    public void addScore(int id_user, int id_rate, BigDecimal money) {
        jdbcTemplate.update(
                "INSERT INTO score (id_user, id_rate, money) VALUES (?, ?, ?)",
                id_user, id_rate, money
        );
    }


    public void addRate(String name, BigDecimal score) {
        jdbcTemplate.update(
                "INSERT INTO rate (name, cost) VALUES (?, ?)",
                name, score
        );
    }

    public void addTransaction(int id_user, long timestamp, BigDecimal change) {
        jdbcTemplate.update(
                "INSERT INTO transactions (id_user, timestamp, change) VALUES (?, ?, ?)",
                id_user, timestamp, change
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

