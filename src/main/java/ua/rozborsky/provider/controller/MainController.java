package ua.rozborsky.provider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by roman on 29.03.2017.
 */
@Controller
public class MainController {

    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    public String users() {

        return "users";
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public String score() {

        return "score";
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String transactions() {

        return "transactions";
    }
}
