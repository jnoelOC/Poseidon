package com.nnk.springboot.TU.api;

import com.nnk.springboot.controllers.api.ApiRatingController;
import com.nnk.springboot.controllers.api.ApiRuleNameController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.impl.RatingService;
import com.nnk.springboot.services.impl.RuleNameService;
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
public class ApiRuleNameControllerTest {

    @InjectMocks
    private ApiRuleNameController apiRuleNameController;

    @Mock
    private RuleNameService ruleNameService;

    RuleName RECORD_1 = new RuleName("name1","descr1", "json1", "template1", "sql1", "part1");
    RuleName RECORD_2 = new RuleName("name2","descr2", "json2", "template2", "sql2", "part2");
    RuleName RECORD_3 = new RuleName("name3","descr3", "json3", "template3", "sql3", "part3");
    @Test
    @DisplayName("Create a RuleName with success")
    public void createARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(ruleNameService.saveRuleName(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<RuleName> re = apiRuleNameController.createRuleNameWithBodyParam(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    @DisplayName("Create a RuleName with failing")
    public void createARecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(ruleNameService.saveRuleName(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<RuleName> re = apiRuleNameController.createRuleNameWithBodyParam(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }


    @Test
    @DisplayName("Update a RuleName with failing")
    public void putARecord_failing() throws Exception {

        // ARRANGE
        Mockito.when(ruleNameService.saveRuleName(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<RuleName> re = apiRuleNameController.updateRuleName(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    @DisplayName("Update a RuleName with success")
    public void putARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(ruleNameService.saveRuleName(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<RuleName> re = apiRuleNameController.updateRuleName(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.OK, re.getStatusCode());

    }
    /*
        @Test
        @DisplayName("Delete a RuleName with failing")
        public void deleteARecord_failing() throws Exception {
            // ARRANGE
            Mockito.when(ruleNameService.findById(1)).thenReturn(null);
            //ACT
            ResponseEntity<Boolean> re = apiRuleNameController.deleteRuleName(1);
            // ASSERT
            Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
        }
    */
    @Test
    @DisplayName("Delete a RuleName with success")
    public void deleteARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(ruleNameService.findById(1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Boolean> re = apiRuleNameController.deleteRuleName(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.FOUND, re.getStatusCode());
    }


    @Test
    @DisplayName("Get RuleName list with success")
    public void getAllRecords_success() throws Exception {
        // ARRANGE
        List<RuleName> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<RuleName> returnRecords = null;

        Mockito.when(ruleNameService.findAllRuleNames()).thenReturn(records);
        //ACT
        returnRecords = apiRuleNameController.findAllRuleNames();
        // ASSERT
        Assert.assertNotNull(returnRecords);
    }

    @Test
    @DisplayName("Get RuleName list with failing")
    public void getAllRecords_failing() throws Exception {
        // ARRANGE
        List<RuleName> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<RuleName> returnedRecords = new ArrayList<>();

        Mockito.when(ruleNameService.findAllRuleNames()).thenReturn(returnedRecords);
        //ACT
        records = apiRuleNameController.findAllRuleNames();
        // ASSERT
        Assert.assertNull(records);
    }

    @Test
    @DisplayName("Find one RuleName with success")
    public void findOneRecord_success() throws Exception {
        // ARRANGE
        Mockito.when(ruleNameService.findById(1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<RuleName> re = apiRuleNameController.findOneRuleName(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.FOUND, re.getStatusCode());
    }

    @Test
    @DisplayName("Find one RuleName with failing")
    public void findOneRecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(ruleNameService.findById(1)).thenReturn(null);
        //ACT
        ResponseEntity<RuleName> re = apiRuleNameController.findOneRuleName(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }
}
