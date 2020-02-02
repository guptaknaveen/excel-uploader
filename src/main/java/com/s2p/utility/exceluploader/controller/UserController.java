package com.s2p.utility.exceluploader.controller;

import com.s2p.utility.exceluploader.logger.Logger;
import com.s2p.utility.exceluploader.model.User;
import com.s2p.utility.exceluploader.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/user-module/addNew")
@SessionAttributes("user")
public class UserController {

    private static Logger logger = Logger.getLogger();

    @Autowired
    private UserManager manager;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("user") User user,
                             BindingResult result, SessionStatus status) {
        logger.info(user);

        manager.addUser(user);

        status.setComplete();
        return "redirect:addNew/success";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(Model model) {
        return "addSuccess";
    }
}