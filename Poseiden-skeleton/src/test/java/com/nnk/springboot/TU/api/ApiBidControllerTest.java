package com.nnk.springboot.TU.api;


import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.api.ApiBidController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.impl.BidListService;
import com.nnk.springboot.util.JsonParser;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WithMockUser(username = "admin")
public class ApiBidControllerTest {

    @InjectMocks
    private ApiBidController apiBidController;

    @Mock
    private BidListService bidListService;


    BidList RECORD_1 = new BidList("Acct1", "Typ1", 1.0);
    BidList RECORD_2 = new BidList("Acct2", "Typ2", 2.0);
    BidList RECORD_3 = new BidList("Acct3", "Typ3", 3.0);

    @Test
    @DisplayName("Get bid list with success")
    public void getAllRecords_success() throws Exception {
        // ARRANGE
        List<BidList> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<BidList> returnRecords = null;

        Mockito.when(bidListService.findAllBids()).thenReturn(records);
        //ACT
        returnRecords = apiBidController.findAllBids();
        // ASSERT
        Assert.assertNotNull(returnRecords);
    }

    @Test
    @DisplayName("Get bid list with failing")
    public void getAllRecords_failing() throws Exception {
        // ARRANGE
        List<BidList> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<BidList> returnedRecords = new ArrayList<>();

        Mockito.when(bidListService.findAllBids()).thenReturn(returnedRecords);
        //ACT
        records = apiBidController.findAllBids();
        // ASSERT
        Assert.assertNull(records);
    }


    @Test
    @DisplayName("Create a bid with success")
    public void createARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(bidListService.saveBidList(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<BidList> re = apiBidController.createBidWithBodyParam(RECORD_1);
        // ASSERT
       Assert.assertNotNull(re.getStatusCode());
    }

    @Test
    @DisplayName("Create a bid with failing")
    public void createARecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(bidListService.saveBidList(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<BidList> re = apiBidController.createBidWithBodyParam(RECORD_1);
        // ASSERT
        Assert.assertNotNull(re.getStatusCode());
    }


    @Test
    @DisplayName("Update a bid with failing")
    public void putARecord_failing() throws Exception {

        // ARRANGE
        Mockito.when(bidListService.saveBidList(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<BidList> re = apiBidController.updateBid(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    @DisplayName("Update a bid with success")
    public void putARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(bidListService.saveBidList(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<BidList> re = apiBidController.updateBid(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.OK, re.getStatusCode());

    }

/*    @Test
    @DisplayName("Delete a bid with failing")
    public void deleteARecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(bidListService.findById(1)).thenReturn(null);
        //ACT
        ResponseEntity<Boolean> re = apiBidController.deleteBid(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }*/

    @Test
    @DisplayName("Delete a bid with success")
    public void deleteARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(bidListService.findById(1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Boolean> re = apiBidController.deleteBid(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.FOUND, re.getStatusCode());
    }




}
