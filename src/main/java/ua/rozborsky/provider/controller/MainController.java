package ua.rozborsky.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rozborsky.provider.classes.*;
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

    @Autowired
    InitDB initDB;

    @Autowired
    Transaction transaction;

    @Autowired
    Filter filter;

    @Autowired
    Time time;

    @Autowired
    DateParser dateParser;

    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    public ModelAndView users(@ModelAttribute("user") User user) {
        //initDB.initDB();                                                        //creating tables and filling them data
        List users = dao.getUsers();
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        modelAndView.addObject("user", user);

        return modelAndView;
    }


    @RequestMapping(value = {"/users"}, method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        int idUser = dao.addUser(user.getName(), user.getSecondName(), user.getAddress());
        dao.addScore(idUser, 1, new BigDecimal(0));
        user.setName(null);
        user.setSecondName(null);
        user.setAddress(null);

        return "redirect:/users";
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

        return modelAndView;
    }


    @RequestMapping(value = "/score/{id}", method = RequestMethod.POST)
    public String changeRate(@PathVariable(value="id") int id, @ModelAttribute("score") Score newScore) {
        Score oldScore = dao.getScore(id);
        if (oldScore.getIdRate() != newScore.getIdRate()) {
            dao.updateRate(newScore.getIdRate(), id);
        }

        return "redirect:/score/" + id;
    }


    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public ModelAndView allTransactions() {
        List<Transaction> transactions = dao.getTransactions();

        return scoreModelAmdView(transactions);
    }


    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ModelAndView filteredTransactions(@ModelAttribute("filter") Filter filter) {
        long startTimestamp = getStartTimestamp(filter.getStartDate());
        long stopTimestamp = getFinishTimestamp(filter.getFinishDate());

        List<Transaction> transactions = dao.getTransactions(filter.getName(), filter.getSurname(),
            startTimestamp, stopTimestamp);

        return scoreModelAmdView(transactions);
    }


    private ModelAndView scoreModelAmdView(List<Transaction> transactions) {
        ModelAndView modelAndView = new ModelAndView("transactions");
        modelAndView.addObject("transactions", transactions);
        modelAndView.addObject("filter", filter);
        List users = dao.getUsers();
        modelAndView.addObject("users", users);

        return modelAndView;
    }


    private long getStartTimestamp(String date) {
        List<Integer> startDate = dateParser.parse(filter.getStartDate());

        if (startDate.isEmpty()){
            return  0;
        } else {
            return time.getTimestamp(startDate.get(0), startDate.get(1), startDate.get(2), 0, 0, 0);
        }
    }


    private long getFinishTimestamp(String date) {
        List<Integer> finishDate = dateParser.parse(filter.getFinishDate());

        if (finishDate.isEmpty()){
            return time.getCurrentTimestamp();
        } else {
            return time.getTimestamp(finishDate.get(0), finishDate.get(1), finishDate.get(2), 0, 0, 0);
        }
    }
}
