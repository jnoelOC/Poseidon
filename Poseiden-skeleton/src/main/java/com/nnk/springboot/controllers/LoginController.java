package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.validators.password.PasswordConstraintValidator;
import com.nnk.springboot.services.impl.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
public class LoginController {

    public static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private BidListService bidListService;


    @Autowired
    private PasswordConstraintValidator passwordConstraintValidator;

    @GetMapping("/login")
    //@RolesAllowed({"USER", "ADMIN"})
    public ModelAndView login(User user) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("login");
        logger.info("Get Login");
        return mav;
    }

/*    @PostMapping("/login")
    //@RolesAllowed({"USER", "ADMIN"})
    public ModelAndView loginPost(@Valid User user, BindingResult bindingResult, Model model) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.setViewName("templates/error");
            logger.error("Post Login error");
            return mav;
        }

 //       model.addAttribute("bids", bidListService.findAllBids());
 //       mav.setViewName("bidList/list");

        logger.info("Post Login");
        return mav;
    }*/

    @GetMapping("/logout")
    @RolesAllowed({"USER", "ADMIN"})
    public ModelAndView logout() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        logger.info("Get Logout");
        return mav;
    }

    @GetMapping("/templates/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("error/403");
        logger.error("Get 403");
        return mav;
    }
}
