package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRatingService {

    List<Rating> findAllRatings() ;

     Rating findById(Integer id);

     Rating saveRating(Rating rating);

    void deleteRating(Rating rating);
}
