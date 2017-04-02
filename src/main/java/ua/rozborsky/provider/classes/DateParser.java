package ua.rozborsky.provider.classes;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by roman on 02.04.2017.
 */
@Component
public class DateParser {
    public List<Integer> parse(String date) {
        List<Integer> list;
        try{
            list = new ArrayList<>();
            String [] dateParts = date.split("-");

            list.add(Integer.valueOf(dateParts[0]));
            list.add(Integer.valueOf(dateParts[1]));
            list.add(Integer.valueOf(dateParts[2]));
        } catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
            list = Collections.emptyList();
        }

        return list;
    }
}
