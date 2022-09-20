package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.validators.password.PasswordConstraintValidator;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class UserController {
    public static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private PasswordConstraintValidator passwordConstraintValidator;

    @Autowired
    private UserRepository userRepository;

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
    public String validate(@Valid User user, BindingResult result, Model model) {

        if(user.getUsername().isBlank() || user.getFullname().isBlank() || user.getPassword().isBlank() || user.getRole().isBlank()){
            model.addAttribute("errorMsg", "Each field is mandatory");
            return "user/add";
        }
        if (!result.hasErrors()) {
            ConstraintValidatorContext context = new ConstraintValidatorContext() {
                @Override
                public void disableDefaultConstraintViolation() {            }
                @Override
                public String getDefaultConstraintMessageTemplate() {
                    return "default template msg";
                }
                @Override
                public ClockProvider getClockProvider() {
                    return null;
                }
                @Override
                public ConstraintViolationBuilder buildConstraintViolationWithTemplate(String messageTemplate) {
                    return null;
                }
                @Override
                public <T> T unwrap(Class<T> type) {
                    return null;
                }
            };

            String pswd = user.getPassword();
            if(passwordConstraintValidator.isValid(pswd, context)) {

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(pswd));

                try {
                    userService.saveUser(user);
                }
                catch(Exception ex){
                    logger.error("Can't add new user : " + ex.getMessage());
                    return "redirect:/user/add";
                }
                model.addAttribute("users", userService.findAll());
                logger.info("Adding new user successfully !");
                return "redirect:/user/list";
            }
            else {
                logger.error("Failure of adding new user");
                return "redirect:/user/add";
            }
        }
        logger.info("Add users (Post) in UserController");
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
                //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        logger.info("Update users (Get) in UserController");
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user,
                             BindingResult result, Model model) {

        if(user.getUsername().isBlank() || user.getFullname().isBlank() || user.getPassword().isBlank() || user.getRole().isBlank()){
            model.addAttribute("errorMsg", "Each field is mandatory");
            logger.error("Each field is mandatory in (Post) upddate users ");
            return "user/update";
        }

        if (result.hasErrors()) {
            logger.error("errors in Update (Post) users");
            return "user/update";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);

        User u1 = userService.saveUser(user);
        if(u1 == null) {
            logger.error("user variable is null");
            return "user/update";
        }
        model.addAttribute("users", userService.findAll());
        logger.info("Update users (Post) in UserController");
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
      try {
          User user = userService.findById(id);
          //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
          userService.deleteUser(user);
          model.addAttribute("users", userService.findAll());
      }
      catch(Exception ex){

      }
        logger.info("delete users in UserController");
        return "redirect:/user/list";
    }


}
