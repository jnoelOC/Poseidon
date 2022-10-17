package com.nnk.springboot.TU.api;

import com.nnk.springboot.controllers.api.ApiRatingController;
import com.nnk.springboot.controllers.api.ApiTradeController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.impl.RatingService;
import com.nnk.springboot.services.impl.TradeService;
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
public class ApiTradeControllerTest {



    @InjectMocks
    private ApiTradeController apiTradeController;

    @Mock
    private TradeService tradeService;

    Trade RECORD_1 = new Trade("accnt1", "type1");
    Trade RECORD_2 = new Trade("accnt2", "type2");
    Trade RECORD_3 = new Trade("accnt3", "type3");

    @Test
    @DisplayName("Create a Trade with success")
    public void createARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(tradeService.saveTrade(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Trade> re = apiTradeController.createTradeWithBodyParam(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    @DisplayName("Create a Trade with failing")
    public void createARecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(tradeService.saveTrade(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<Trade> re = apiTradeController.createTradeWithBodyParam(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }


    @Test
    @DisplayName("Update a Trade with failing")
    public void putARecord_failing() throws Exception {

        // ARRANGE
        Mockito.when(tradeService.saveTrade(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<Trade> re = apiTradeController.updateTrade(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    @DisplayName("Update a Trade with success")
    public void putARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(tradeService.saveTrade(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Trade> re = apiTradeController.updateTrade(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.OK, re.getStatusCode());

    }
    /*
        @Test
        @DisplayName("Delete a Trade with failing")
        public void deleteARecord_failing() throws Exception {
            // ARRANGE
            Mockito.when(tradeService.findById(1)).thenReturn(null);
            //ACT
            ResponseEntity<Boolean> re = apiTradeController.deleteTrade(1);
            // ASSERT
            Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
        }
    */
    @Test
    @DisplayName("Delete a Trade with success")
    public void deleteARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(tradeService.findById(1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Boolean> re = apiTradeController.deleteTrade(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.FOUND, re.getStatusCode());
    }


    @Test
    @DisplayName("Get Trade list with success")
    public void getAllRecords_success() throws Exception {
        // ARRANGE
        List<Trade> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<Trade> returnRecords = null;

        Mockito.when(tradeService.findAllTrades()).thenReturn(records);
        //ACT
        returnRecords = apiTradeController.findAllTrades();
        // ASSERT
        Assert.assertNotNull(returnRecords);
    }

    @Test
    @DisplayName("Get Trade list with failing")
    public void getAllRecords_failing() throws Exception {
        // ARRANGE
        List<Trade> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<Trade> returnedRecords = new ArrayList<>();

        Mockito.when(tradeService.findAllTrades()).thenReturn(returnedRecords);
        //ACT
        records = apiTradeController.findAllTrades();
        // ASSERT
        Assert.assertNull(records);
    }

    @Test
    @DisplayName("Find one Trade with success")
    public void findOneRecord_success() throws Exception {
        // ARRANGE
        Mockito.when(tradeService.findById(1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Trade> re = apiTradeController.findOneTrade(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.FOUND, re.getStatusCode());
    }

    @Test
    @DisplayName("Find one Trade with failing")
    public void findOneRecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(tradeService.findById(1)).thenReturn(null);
        //ACT
        ResponseEntity<Trade> re = apiTradeController.findOneTrade(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }
}
