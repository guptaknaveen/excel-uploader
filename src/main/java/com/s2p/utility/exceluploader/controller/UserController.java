package com.s2p.utility.exceluploader.controller;

import com.s2p.utility.exceluploader.logger.Logger;
import com.s2p.utility.exceluploader.model.User;
import com.s2p.utility.exceluploader.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
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

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    @ResponseBody
    public String submitForm(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        logger.info(user);

        manager.addUser(user);

        return "User added";
    }
}