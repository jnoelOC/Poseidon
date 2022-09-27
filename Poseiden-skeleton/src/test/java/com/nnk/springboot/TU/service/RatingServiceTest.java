package com.nnk.springboot.TU.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.impl.RatingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {


    @InjectMocks
    private RatingService ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Test
    @DisplayName("find all ratings")
    void FindAllRatings_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        List<Rating> ratings = new ArrayList<>();
        Rating rating1 = new Rating("moody1", "sand1", "fitch1",1);
        Rating rating2 = new Rating("moody2", "sand2", "fitch2",2);
        ratings.add(rating1);
        ratings.add(rating2);

        when(ratingRepository.findAll()).thenReturn(ratings);
        // Act
        List<Rating> allRatings = ratingService.findAllRatings();
        if (allRatings.isEmpty()) {
            ret = false;
        }

        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("find Rating by id")
    void FindRatingById_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        Optional<Rating> rating3 = Optional.of(new Rating("moody3", "sand3", "fitch3",3));
        rating3.ifPresent( ratingValue ->
                when(ratingRepository.findById(1)).thenReturn(rating3)
        );
        // Act
        Rating rating2 = ratingService.findById(1);
        if (rating2 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("save Rating")
    void SaveRating_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        Rating r1 = new Rating("moody1", "sand1", "fitch1",1);
        Rating r3 = new Rating("moody3", "sand3", "fitch3",3);

        when(ratingRepository.save(r3)).thenReturn(r1);
        // Act
        Rating r2 = ratingService.saveRating(r3);
        if (r2 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("delete Rating")
    void DeleteRating_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        Rating r3 = new Rating("moody3", "sand3", "fitch3",3);
        ratingRepository.delete(Mockito.any(Rating.class));
        // Act
        ratingService.deleteRating(r3);
        // Assert
        assertTrue(ret);
    }
}
