package ua.rozborsky.provider.classes;

import org.springframework.stereotype.Component;

/**
 * Created by roman on 01.04.2017.
 */
@Component
public class Time {

    public long getTimestamp(int year, int month, int day, int hour, int minute, int sec){
        return java.sql.Timestamp.valueOf(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + sec).getTime();
    }
}
