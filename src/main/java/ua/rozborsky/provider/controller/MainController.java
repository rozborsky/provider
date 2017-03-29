package ua.rozborsky.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.rozborsky.provider.interfaces.DAO;

/**
 * Created by roman on 29.03.2017.
 */
@Controller
public class MainController {

    @Autowired
    DAO dao;


    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    public String users() {

        //dao.initDB();                                                         creating tables and filling them data
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
