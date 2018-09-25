package org.launchcode.prospector6.controllers;


import org.launchcode.prospector6.models.Prospect;
import org.launchcode.prospector6.models.Referrer;
import org.launchcode.prospector6.models.State;
import org.launchcode.prospector6.models.data.ReferrerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.launchcode.prospector6.controllers.UserController.currentUser;

@Controller
@RequestMapping("referrer")
public class ReferrerController {
    @Autowired
    private ReferrerDao referrerDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("referrers", referrerDao.findByUserId(currentUser.getId()));
        model.addAttribute("title", "My Referrers");
        model.addAttribute(new Referrer());
        return "referrer/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddReferrerForm(Model model) {
        model.addAttribute("title", "Add Referrer");
        model.addAttribute(new Referrer());
        model.addAttribute("states", State.values());
        return "referrer/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddReferrerForm(@ModelAttribute @Valid Referrer newReferrer,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Referrer");
            return "redirect:referrer/" + newReferrer.getId();
        }
        /*if (UserController.currentUser !=null) {
            newReferrer.setUser(UserController.currentUser);
        } */
        newReferrer.setUser(currentUser);
        referrerDao.save(newReferrer);
        return "redirect:/referrer";
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewReferrer(Model model, @PathVariable int id) {

        Referrer refer = referrerDao.findByIdAndUserId(id, currentUser.getId());
        List<Prospect> prospects = refer.getProspects();
        model.addAttribute("title", refer.toString());
        model.addAttribute("referrer", refer);

        return "referrer/view";

    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String displayEditReferrerForm(@PathVariable Integer id, Model model){
        Referrer ref = referrerDao.findByIdAndUserId(id, currentUser.getId());
        model.addAttribute("title", "Edit: " + ref.toString());
        model.addAttribute("referrer", ref);
        model.addAttribute("states", State.values());

        return "referrer/edit";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String processEditReferrerForm( @Valid Referrer referrer, @PathVariable Integer id){
        referrerDao.save(referrer);
        return "redirect:/referrer/view/{id}";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveReferrerForm(Model model) {
        model.addAttribute("referrers", referrerDao.findByUserId(currentUser.getId()));
        model.addAttribute("title", "Remove Referrer");
        return "referrer/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveReferrerForm(@RequestParam Optional<int[]> referrerids) {
        if (referrerids.isPresent()) {
            for (int referrerid : referrerids.get()) {
                referrerDao.delete(referrerid);
            }
        }
        return "redirect:/referrer";
    }
}

