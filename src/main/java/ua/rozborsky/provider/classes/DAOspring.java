package ua.rozborsky.provider.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.rozborsky.provider.interfaces.DAO;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    }
}

