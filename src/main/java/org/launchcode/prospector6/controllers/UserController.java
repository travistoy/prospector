package org.launchcode.prospector6.controllers;

import org.launchcode.prospector6.models.Role;
import org.launchcode.prospector6.models.User;
import org.launchcode.prospector6.models.data.ProspectDao;
import org.launchcode.prospector6.models.data.RoleDao;
import org.launchcode.prospector6.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ProspectDao prospectDao;

    @Autowired
    private RoleDao roleDao;


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

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm( @RequestParam String username, @RequestParam String password, Model model, Errors errors, HttpServletRequest request, HttpServletResponse response) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            return "login";}
        if (!username.equals(userDao.findByUsername(username))) {
            String username_error = "Usernames Do Not Match.";
            model.addAttribute("username_error", username_error);
            return "login";
        }
        User currentUser = userDao.findByUsername(username);
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        String plainPassword = password;
        String encryptedPassword = enc.encode(plainPassword);
        if (!encryptedPassword.equals(currentUser.getPassword())) {
            String password_error = "Passwords Do Not Match.";
            model.addAttribute("password_error", password_error);
            return "login";}
        autoLogin(currentUser.getUsername(), plainPassword, request);
        return "redirect:view/"+currentUser.getId();
    }


    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {
        model.addAttribute("title", "Sign Up");
        model.addAttribute(new User());
        return "signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processAddUserForm(@ModelAttribute @Valid User newUser, @RequestParam String password, @RequestParam String verify,
                                     Errors errors, Model model, HttpServletRequest request, HttpServletResponse response, Role role) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Sign Up");

            return "signup";
            }
        if (!newUser.getPassword().equals(newUser.getVerify())) {
            String verify_error = "Passwords Do Not Match.";
            model.addAttribute("verify_error", verify_error);
            return "signup";
        }

        Role roleCheck;
        roleCheck = roleDao.findByName("ROLE_USER");

        if(roleCheck != null){
            newUser.setRoles(Arrays.asList(new Role("ROLE_USER")));
        }
        else {
            newUser.setRoles(Arrays.asList(roleDao.findByName("ROLE_USER")));
        }


        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        String plainPassword = newUser.getPassword();
        String encryptedPassword = enc.encode(plainPassword);
        newUser.setPassword(encryptedPassword);
        newUser.setEnabled(true);
        User saved = userDao.save(newUser);
        autoLogin(newUser.getUsername(), plainPassword, request);

        return "redirect:view/"+saved.getId();
        }

        @Resource
        private AuthenticationManager authManager;

        public boolean autoLogin( String username, String password, HttpServletRequest request) {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authentication );

            //this step is important, otherwise the new login is not in session which is required by Spring Security
            request.getSession(true).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());


            return true;
        }


}