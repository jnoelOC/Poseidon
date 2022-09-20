package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.impl.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RatingController {
    public static final Logger logger = LogManager.getLogger(UserController.class);
@Autowired
private RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        List<Rating> ratings = ratingService.findAllRatings();
        logger.info("find all ratings in list");
        model.addAttribute("ratings", ratings);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm() {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        if (!result.hasErrors()) {
            ratingService.saveRating(rating);
            logger.info("validate rating");
            model.addAttribute("ratings", ratingService.findAllRatings());
            return "redirect:/rating/list";
        }
        logger.error("validate ratings in error");
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        Rating rating = ratingService.findById(id);  //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        logger.info("Get update ratings by id");
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list

        if (result.hasErrors()) {
            logger.error("Post update ratings in error");
            return "rating/update";
        }
        rating.setId(id);

        Rating rating1 = ratingService.saveRating(rating);
        if(rating1 == null) {
            logger.error("rating variable is null");
            return "rating/update";
        }
        logger.info("Post update ratings okay, display all ratings in list");
        model.addAttribute("rating", ratingService.findAllRatings());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        Rating rating = ratingService.findById(id);
        //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        ratingService.deleteRating(rating);
        logger.info("delete rating by id");
        model.addAttribute("rating", ratingService.findAllRatings());
        return "redirect:/rating/list";
    }
}
