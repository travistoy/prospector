package org.launchcode.prospector6.controllers;

import org.launchcode.prospector6.models.Referrer;
import org.launchcode.prospector6.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dcannarozzi on 10/28/17.
 */
@Controller
@RequestMapping("user")
public class UserController {

    public static User currentUser = new User();
    User[] users;

    public UserController() {
        users = new User[2];
        users[0] = new User();
        users[0].setId(1);
        users[0].setUserFirst("John");
        users[0].setUserLast("Doe");
        users[1] = new User();
        users[1].setId(2);
        users[1].setUserFirst("Jane");
        users[1].setUserLast("Doe");
    }

    @RequestMapping(value = "")
    public String index(Model model) {
        // uncomment when replaced hardcoded values with UserDao
        //User[] users = userDao.findAll();

        model.addAttribute("users", users);
        model.addAttribute("title", "Users");
        return "user/index";
    }

    @RequestMapping(value="view/{id}")
    public String view(Model model, @PathVariable int id) {
        // use the statically defined users for now until a DAO is created
        currentUser = users[id-1];
        return "referrer/index";
    }
}
