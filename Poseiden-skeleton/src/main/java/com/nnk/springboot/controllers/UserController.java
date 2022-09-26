package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.validators.password.PasswordConstraintValidator;
import com.nnk.springboot.services.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class UserController {
    public static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private PasswordConstraintValidator passwordConstraintValidator;

    @Autowired
    private UserService userService;


    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.findAll());
        logger.info("List of users in UserController");
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user) {
        logger.info("Add users (Get) in UserController");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) throws SQLException{

        if(user.getUsername().isBlank() || user.getFullname().isBlank() || user.getPassword().isBlank() || user.getRole().isBlank()){
            model.addAttribute("errorMsg", "Chaque champ est obligatoire");
            return "user/add";
        }
        if (!result.hasErrors()) {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.saveUser(user);
            model.addAttribute("users", userService.findAll());
            logger.info("Adding new user successfully !");
            return "redirect:/user/list";
        }

            logger.error("Can't add new user : " + result.getFieldError());
            model.addAttribute("errorMsg", "Ne peut pas sauvegarder un nouvel utilisateur");

            return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        logger.info("Update users (Get) in UserController");
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user,
                             BindingResult result, Model model) {

        if(user.getUsername().isBlank() || user.getFullname().isBlank() || user.getPassword().isBlank() || user.getRole().isBlank()){
            model.addAttribute("errorMsg", "Chaque champ est obligatoire");
            logger.error("Each field is mandatory in (Post) upddate users ");
            return "user/update";
        }

        if (result.hasErrors()) {
            logger.error("errors in Update (Post) users" + result.getFieldError());
            return "user/update";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);

        User u1 = userService.saveUser(user);
        if(u1 == null) {
            logger.error("user variable is null");
            model.addAttribute("errorMsg", "Ne peut pas sauvegarder un nouvel utilisateur");
            return "user/update";
        }
        model.addAttribute("users", userService.findAll());
        logger.info("Update users (Post) in UserController");
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {

          User user = userService.findById(id);
          userService.deleteUser(user);
          model.addAttribute("users", userService.findAll());
          logger.info("delete users in UserController");
          return "redirect:/user/list";
    }


}
