package ua.rozborsky.provider.classes;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

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
}
