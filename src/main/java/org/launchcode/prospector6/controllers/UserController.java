package org.launchcode.prospector6.controllers;

import org.launchcode.prospector6.models.User;
import org.launchcode.prospector6.models.data.ProspectDao;
import org.launchcode.prospector6.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute(new User());
        return "user/index";
    }

    @RequestMapping(value="view/{id}")
    public String view(Model model, @PathVariable int id) {
        currentUser = userDao.findOne(id);
        model.addAttribute("title", currentUser);
        model.addAttribute("totalProspects", prospectDao.countByUserId(currentUser.getId()));
        model.addAttribute("totalPremium", prospectDao.getTotalPremiumByUserId(currentUser.getId()));
        model.addAttribute("totalCommission", prospectDao.getTotalCommissionByUserId(currentUser.getId()));

        return "user/view";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {
        model.addAttribute("title", "Sign Up");
        model.addAttribute(new User());
        return "signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processAddUserForm(@ModelAttribute @Valid User newUser, @RequestParam String password, @RequestParam String verify,
                                     Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Sign Up");

            return "signup";
            }
        if (!newUser.getPassword().equals(newUser.getVerify())) {
            String verify_error = "Passwords Do Not Match.";
            model.addAttribute("verify_error", verify_error);
            return "signup";
        }

        userDao.save(newUser);
        return "redirect:";
        }


}