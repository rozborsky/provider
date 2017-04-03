package ua.rozborsky.provider.classes;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by roman on 01.04.2017.
 */
@Component
public class Time {

    public long getTimestamp(int year, int month, int day, int hour, int minute, int sec){
        return Timestamp.valueOf(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + sec).getTime();
    }

    public long getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis()).getTime();
    }

    public String getDateFromTimestamp (long timestamp) {
        Date time = new java.util.Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("y-MM-dd HH:mm:ss");

        return formatter.format(time).replaceAll("\\s", " _ ");
    }

    public String getCurrentDay() {
        Date currentTime = new java.util.Date(new Timestamp(System.currentTimeMillis()).getTime());
        DateFormat formatter = new SimpleDateFormat("dd");

        return formatter.format(currentTime);
    }

    public int getDayFromTimestamp (long timestamp) {
        Date time = new java.util.Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("dd");

        return Integer.valueOf(formatter.format(time));
    }

    public int getMonthFromTimestamp (long timestamp) {
        Date time = new java.util.Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("MM");

        return Integer.valueOf(formatter.format(time));
    }
}
