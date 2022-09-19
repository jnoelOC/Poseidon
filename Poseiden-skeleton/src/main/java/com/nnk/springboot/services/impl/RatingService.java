package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService implements IRatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating findById(Integer id) {return ratingRepository.findById(id).get(); }

    public Rating saveRating(Rating rating){ return ratingRepository.save(rating); }

    public void deleteRating(Rating rating){ ratingRepository.delete(rating); }
}
