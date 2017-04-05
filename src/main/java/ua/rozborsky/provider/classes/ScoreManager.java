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
    private long milisecondMonth = 2419200000L;
    private long milisecondYear = 31536000000L;
    private String providerMail = "provider@gmail.com";
    private String providerMailPassword = "password";

    @Autowired
    Time time;

    @Autowired
    DAO dao;

    @Autowired
    Mail mail;

    public void checkUsers(){
        int currentDay = Integer.valueOf(time.getCurrentDay());
        long currentTimestamp = time.getCurrentTimestamp();

        List<Score> scores = dao.getScores();
        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);

            int idRate = score.getIdRate();
            long lastPaymentTimestamp = dao.getDateLastPayment(score.getIdUser());
            int lastPaymentDay = time.getDayFromTimestamp(lastPaymentTimestamp);

            if (((idRate == 1)
                    && ((currentTimestamp - lastPaymentTimestamp) > milisecondMonth)
                    && (currentDay > lastPaymentDay))
                    ||
                    ((idRate == 2)
                            && ((currentTimestamp - lastPaymentTimestamp) > milisecondYear)
                            && (currentDay > lastPaymentDay))) {

                BigDecimal fee = dao.getRate(score.getIdRate()).getCost();
                BigDecimal newMoney = score.getMoney().subtract(fee);

                if (newMoney.compareTo(new BigDecimal(0)) >= 0) {
                    dao.takeFee(score.getIdUser(), newMoney);
                } else {
                    sendLetter(score);
                }
            }
        }
    }

    private void sendLetter(Score score) {
        mail.initAddress(providerMail, providerMailPassword);
        mail.send(dao.getUser(score.getIdUser()).getName(),
                dao.getUser(score.getIdUser()).getSecondName(),
                dao.getUser(score.getIdUser()).geteMail());
    }
}
