package org.launchcode.prospector6.controllers;

import org.launchcode.prospector6.models.User;
import org.launchcode.prospector6.models.data.ProspectDao;
import org.launchcode.prospector6.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dcannarozzi on 10/28/17.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProspectDao prospectDao;

    public static User currentUser = new User();
    List<User> users = new ArrayList<User>();


    public UserController(){

    }

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("title", "Users");
        return "user/index";
    }

    @RequestMapping(value="view/{id}")
    public String view(Model model, @PathVariable int id) {
        User currentUser = userDao.findOne(id);
        model.addAttribute("title", currentUser);
        model.addAttribute("prospects", prospectDao.findAll());
        return "user/view";
    }
}