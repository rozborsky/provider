package ua.rozborsky.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rozborsky.provider.classes.Rate;
import ua.rozborsky.provider.classes.Score;
import ua.rozborsky.provider.classes.User;
import ua.rozborsky.provider.interfaces.DAO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by roman on 29.03.2017.
 */
@Controller
public class MainController {

    @Autowired
    DAO dao;

    @Autowired
    User user;


    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    public ModelAndView users(@ModelAttribute("user") User user) {
        //dao.initDB();                                                         //creating tables and filling them data
        List users = dao.getUsers();
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = {"/users"}, method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("user") User user) {
        int idUser = dao.addUser(user.getName(), user.getSecondName(), user.getAddress());
        dao.addScore(idUser, 1, new BigDecimal(0));

        List users = dao.getUsers();
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/score/{id}", method = RequestMethod.GET)
    public ModelAndView score(@PathVariable(value="id") int id) {
        User user = dao.getUser(id);
        Score score = dao.getScore(id);
        Rate rate = dao.getRate(score.getIdRate());
        List <Rate> rateList = dao.rateList();

        ModelAndView modelAndView = new ModelAndView("score");
        modelAndView.addObject("user", user);
        modelAndView.addObject("score", score);
        modelAndView.addObject("currentRate", rate);
        modelAndView.addObject("rateList", rateList);
        System.out.println(rate.getName());
        return modelAndView;
    }

    @RequestMapping(value = "/score/{id}", method = RequestMethod.POST)
    public ModelAndView changeRate(@PathVariable(value="id") int id, @ModelAttribute("score") Score newScore) {
        Score oldScore = dao.getScore(id);
        if (oldScore.getIdRate() != newScore.getIdRate()) {
            dao.updateRate(newScore.getIdRate(), id);
        }

        User user = dao.getUser(id);
        Score score = dao.getScore(id);
        Rate rate = dao.getRate(score.getIdRate());
        List <Rate> rateList = dao.rateList();

        ModelAndView modelAndView = new ModelAndView("score");
        modelAndView.addObject("user", user);
        modelAndView.addObject("score", score);
        modelAndView.addObject("currentRate", rate);
        modelAndView.addObject("rateList", rateList);

        return modelAndView;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String transactions() {

        return "transactions";
    }
}
