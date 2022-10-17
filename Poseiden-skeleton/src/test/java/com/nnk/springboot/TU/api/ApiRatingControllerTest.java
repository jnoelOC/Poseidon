package com.nnk.springboot.TU.api;


import com.nnk.springboot.controllers.api.ApiCurvePointController;
import com.nnk.springboot.controllers.api.ApiRatingController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.impl.CurvePointService;
import com.nnk.springboot.services.impl.RatingService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ApiRatingControllerTest {

    @InjectMocks
    private ApiRatingController apiRatingController;

    @Mock
    private RatingService ratingService;

    Rating RECORD_1 = new Rating("moodys1","sand1", "fitch1", 1);
    Rating RECORD_2 = new Rating("moodys2","sand2", "fitch2", 2);
    Rating RECORD_3 = new Rating("moodys3","sand3", "fitch3", 3);

    @Test
    @DisplayName("Create a Rating with success")
    public void createARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(ratingService.saveRating(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Rating> re = apiRatingController.createRatingWithBodyParam(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    @DisplayName("Create a Rating with failing")
    public void createARecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(ratingService.saveRating(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<Rating> re = apiRatingController.createRatingWithBodyParam(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }


    @Test
    @DisplayName("Update a Rating with failing")
    public void putARecord_failing() throws Exception {

        // ARRANGE
        Mockito.when(ratingService.saveRating(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<Rating> re = apiRatingController.updateRating(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    @DisplayName("Update a Rating with success")
    public void putARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(ratingService.saveRating(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Rating> re = apiRatingController.updateRating(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.OK, re.getStatusCode());

    }
    /*
        @Test
        @DisplayName("Delete a Rating with failing")
        public void deleteARecord_failing() throws Exception {
            // ARRANGE
            Mockito.when(ratingService.findById(1)).thenReturn(null);
            //ACT
            ResponseEntity<Boolean> re = apiRatingController.deleteRating(1);
            // ASSERT
            Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
        }
    */
    @Test
    @DisplayName("Delete a Rating with success")
    public void deleteARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(ratingService.findById(1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Boolean> re = apiRatingController.deleteRating(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.FOUND, re.getStatusCode());
    }


    @Test
    @DisplayName("Get Rating list with success")
    public void getAllRecords_success() throws Exception {
        // ARRANGE
        List<Rating> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<Rating> returnRecords = null;

        Mockito.when(ratingService.findAllRatings()).thenReturn(records);
        //ACT
        returnRecords = apiRatingController.findAllRatings();
        // ASSERT
        Assert.assertNotNull(returnRecords);
    }

    @Test
    @DisplayName("Get Rating list with failing")
    public void getAllRecords_failing() throws Exception {
        // ARRANGE
        List<Rating> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<Rating> returnedRecords = new ArrayList<>();

        Mockito.when(ratingService.findAllRatings()).thenReturn(returnedRecords);
        //ACT
        records = apiRatingController.findAllRatings();
        // ASSERT
        Assert.assertNull(records);
    }

    @Test
    @DisplayName("Find one Rating with success")
    public void findOneRecord_success() throws Exception {
        // ARRANGE
        Mockito.when(ratingService.findById(1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Rating> re = apiRatingController.findOneRating(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.FOUND, re.getStatusCode());
    }

    @Test
    @DisplayName("Find one Rating with failing")
    public void findOneRecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(ratingService.findById(1)).thenReturn(null);
        //ACT
        ResponseEntity<Rating> re = apiRatingController.findOneRating(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }
}
