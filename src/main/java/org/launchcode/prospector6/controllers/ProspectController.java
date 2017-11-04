package org.launchcode.prospector6.controllers;


import org.launchcode.prospector6.models.LineType;
import org.launchcode.prospector6.models.Prospect;
import org.launchcode.prospector6.models.Referrer;
import org.launchcode.prospector6.models.State;
import org.launchcode.prospector6.models.data.ProspectDao;
import org.launchcode.prospector6.models.data.ReferrerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("prospect")
public class ProspectController {
    @Autowired
    private ProspectDao prospectDao;

    @Autowired
    private ReferrerDao referrerDao;


    @RequestMapping(value = "")
    public String index(Model model ) {

        model.addAttribute("prospects", prospectDao.findAll());
        model.addAttribute("title", "My Prospects");
        model.addAttribute(new Prospect());
        return "prospect/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddProspectForm(Model model) {
        model.addAttribute("title", "Add Prospect");
        model.addAttribute(new Prospect());
        model.addAttribute("referrers", referrerDao.findAll());
        model.addAttribute("lineTypes", LineType.values());
        model.addAttribute("states", State.values());
        return "prospect/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddProspectForm(@ModelAttribute @Valid Prospect newProspect,
                                         Errors errors, @RequestParam int referrerId, Model model) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Prospect");
            model.addAttribute("referrers", referrerDao.findAll());
            return "prospect/add";
        }
        Referrer ref;
        if (referrerId == 0){
             ref = null;
        }
        else {
            ref = referrerDao.findOne(referrerId);
        }
        newProspect.setReferrer(ref);
        newProspect.setCreated(LocalDate.now());
        prospectDao.save(newProspect);
        return "redirect:";
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewProspect( Model model, @PathVariable int id){

            Prospect pros = prospectDao.findOne(id);
            model.addAttribute("title", pros.toString());
            model.addAttribute("prospect", pros);
                return "prospect/view";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String displayEditProspectForm(@PathVariable int id, Model model){
        Prospect props = prospectDao.findOne(id);
        model.addAttribute("title", "Edit: " + props.toString());
        model.addAttribute("prospect", props);
        model.addAttribute("referrers", referrerDao.findAll());
        model.addAttribute("states", State.values());
        model.addAttribute("lineTypes", LineType.values());
        return "prospect/edit";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String processEditForm(@Valid Prospect prospect,Errors errors, Model model, @RequestParam int referrerId, @PathVariable Integer id){
        if (errors.hasErrors()){
            model.addAttribute("referrers", referrerDao.findAll());
            return "prospect/add";}
        Referrer ref;
        if (referrerId == 0){
            ref = null;
        }
        else {
            ref = referrerDao.findOne(referrerId);
        }
        prospect.setReferrer(ref);
        prospectDao.save(prospect);

        return "redirect:/prospect/view/{id}";
    }
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveProspectForm(Model model) {
        model.addAttribute("prospects", prospectDao.findAll());
        model.addAttribute("title", "Remove Prospect");
        return "prospect/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveProspectForm(@RequestParam int[] prospectIds) {
        for (int prospectId : prospectIds) {
            prospectDao.delete(prospectId);
        }
        return "redirect:";
    }

}
