package ua.rozborsky.provider.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.rozborsky.provider.interfaces.DAO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by roman on 03.04.2017.
 */
@Component
public class ScoreManager {

    @Autowired
    Time time;

    @Autowired
    DAO dao;

    @Autowired
    Mail mail;

    public void checkUsers(){
        int currentDay = Integer.valueOf(time.getCurrentDay());
        long milisecondMonth = 2419200000L;
        long milisecondYear = 31536000000L;
        long currentTimestamp = time.getCurrentTimestamp();

        List<Score> scores = dao.getScores();
        for (int i = 0; i < scores.size(); i++) {
            int idRate = (scores.get(i)).getIdRate();
            System.out.println(i + " - i");


            System.out.println(scores.get(i).getIdUser());
            long lastPaymentTimestamp = dao.getDateLastPayment(scores.get(i).getIdUser());
            int lastPaymentDay = time.getDayFromTimestamp(lastPaymentTimestamp);

            if (((idRate == 1)
                    && ((currentTimestamp - lastPaymentTimestamp) > milisecondMonth)
                    && (currentDay > lastPaymentDay))
                    ||
                    ((idRate == 2)
                            && ((currentTimestamp - lastPaymentTimestamp) > milisecondYear)
                            && (currentDay > lastPaymentDay))) {

                BigDecimal fee = dao.getRate(scores.get(i).getIdRate()).getCost();
                BigDecimal newMoney = scores.get(i).getMoney().subtract(fee);
                if (newMoney.compareTo(new BigDecimal(0)) >= 0) {
                    dao.takeFee(scores.get(i).getIdUser(), newMoney);
                } else {
                    mail.initAddress("provider@gmail.com", "password");
                    mail.send(dao.getUser(scores.get(i).getIdUser()).getName(),
                            dao.getUser(scores.get(i).getIdUser()).getSecondName(),
                            dao.getUser(scores.get(i).getIdUser()).getName());
                }
            }
        }
    }

    public void addMoney(int userId, BigDecimal money) {

    }

    private void takeFee(int userId, BigDecimal money) {

    }
}
