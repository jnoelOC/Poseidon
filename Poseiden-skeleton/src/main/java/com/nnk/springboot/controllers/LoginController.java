package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.BidListService;
import com.nnk.springboot.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
//@RequestMapping("app")
public class LoginController {

    @Autowired
    private BidListService bidListService;

    @GetMapping("/login")
    @RolesAllowed({"USER", "ADMIN"})
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
/*
    @GetMapping("/*")
    public String getGithub() {
        return "Welcome, github user !";
    }
*/
    @PostMapping("/login")
    @RolesAllowed({"USER", "ADMIN"})
    public ModelAndView loginPost(Model model) {
        ModelAndView mav = new ModelAndView();

            model.addAttribute("bids", bidListService.findAllBids());
//            mav.setViewName("home");
        mav.setViewName("bidList/list");

        return mav;
    }

    @GetMapping("/logout")
    @RolesAllowed({"USER", "ADMIN"})
    public ModelAndView logout() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }


/*
    @GetMapping("/app-logout")
    @RolesAllowed({"USER", "ADMIN"})
    public ModelAndView logout() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
*/
/*
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }
*/
    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
