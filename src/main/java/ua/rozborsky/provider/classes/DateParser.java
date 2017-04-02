package ua.rozborsky.provider.classes;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 02.04.2017.
 */
@Component
public class DateParser {
    public List<Integer> parse(String date) {
        String [] dateParts = date.split("-");
        List<Integer> list = new ArrayList<>();

        list.add(Integer.valueOf(dateParts[0]));
        list.add(Integer.valueOf(dateParts[1]));
        list.add(Integer.valueOf(dateParts[2]));

        return list;
    }
}
