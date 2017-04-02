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

    private JdbcTemplate jdbcTemplate;

    DAOspring() {

    }

    @PostConstruct
    private void init() {
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


    public List<Transaction> getTransactions() {
        return jdbcTemplate.query(
                "SELECT * FROM transactions A LEFT JOIN users B ON A.id_user=B.id",
                new RowMapper<Transaction>() {
                    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Transaction transaction = new Transaction();
                        transaction.setId(rs.getInt("id"));
                        transaction.setIdUser(rs.getInt("id_user"));
                        transaction.setDate(rs.getLong("timestamp"));
                        transaction.setName(rs.getString("name"));
                        transaction.setSurname(rs.getString("second_name"));
                        transaction.setChange(new BigDecimal(rs.getString("change")));
                        return transaction;
                    }
                });
    }

    public List<Transaction> getFilteredTransactions(String name, String surname, long startDate, long finishDate) {
        return jdbcTemplate.query(
                "SELECT * FROM transactions A LEFT JOIN users B ON A.id_user=B.id WHERE A.timestamp BETWEEN "
                        + startDate + " AND " + finishDate + "ORDER BY A.timestamp",
                new RowMapper<Transaction>() {
                    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Transaction transaction = new Transaction();
                        transaction.setId(rs.getInt("id"));
                        transaction.setIdUser(rs.getInt("id_user"));
                        transaction.setDate(rs.getLong("timestamp"));
                        transaction.setName(rs.getString("name"));
                        transaction.setSurname(rs.getString("second_name"));
                        transaction.setChange(new BigDecimal(rs.getString("change")));
                        return transaction;
                    }
                });
    }

    public void updateRate(int idRate, int id) {
        jdbcTemplate.update("UPDATE score SET id_rate=? WHERE id=?",
                idRate, id);
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
}

