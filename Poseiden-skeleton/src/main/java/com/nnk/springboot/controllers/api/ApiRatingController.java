package com.nnk.springboot.controllers.api;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.IBidListService;
import com.nnk.springboot.services.IRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiRatingController {

    public static final Logger logger = LogManager.getLogger(ApiRatingController.class);

    @Autowired
    IRatingService ratingService;

    @GetMapping("api/rating/list")
    public List<Rating> findAllRatings() {

        List<Rating> lr = ratingService.findAllRatings();

        if (lr.isEmpty()) {
            logger.error("Find all Ratings : status Non trouvé.");
            return null;
        }
        logger.info("Ratings trouvés.");
        return lr;
    }

    @PutMapping("api/rating/update")
    public ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {

        Rating r = ratingService.saveRating(rating);
        if (r == null) {
            logger.error("update Rating : status Non trouvé.");
            return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
        } else {
            logger.info("Update Rating : status Ok.");
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
    }

    @DeleteMapping("api/rating/delete")
    public ResponseEntity<Boolean> deleteRating(@RequestParam Integer id) {

        try {
            Rating r = ratingService.findById(id);
            ratingService.deleteRating(r);
        } catch (Exception ex) {
            logger.error("Error delete Rating : "+ ex.getMessage());
            return new ResponseEntity<>(true, HttpStatus.NOT_FOUND);
        }

        logger.info("Delete Rating : status Trouvé.");
        return new ResponseEntity<>(true, HttpStatus.FOUND);

    }

    @PostMapping("api/rating/create")
    public ResponseEntity<Rating> createRatingWithBodyParam(@RequestBody Rating rating) {

        Rating r = ratingService.saveRating(rating);
        if (r == null) {
            logger.error("create Rating : status Non trouvé.");
            return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
        } else {
            logger.info("Create Rating : status Créé.");
            return new ResponseEntity<>(r, HttpStatus.CREATED);
        }

    }


    @GetMapping("api/rating/getOne")
    public ResponseEntity<Rating> findOneRating(@RequestParam Integer id) {

        Rating r = ratingService.findById(id);
        if (r == null) {
            logger.error("FindOneRating : status Non trouvé.");
            return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
        } else {
            logger.info("status Rating trouvé.");
            return new ResponseEntity<>(r, HttpStatus.FOUND);
        }
    }

}
