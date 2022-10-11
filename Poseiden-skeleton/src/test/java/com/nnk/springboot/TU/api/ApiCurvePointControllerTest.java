package com.nnk.springboot.TU.api;

import com.nnk.springboot.controllers.api.ApiBidController;
import com.nnk.springboot.controllers.api.ApiCurvePointController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.impl.BidListService;
import com.nnk.springboot.services.impl.CurvePointService;
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
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WithMockUser(username = "admin")
public class ApiCurvePointControllerTest {
    @InjectMocks
    private ApiCurvePointController apiCPController;

    @Mock
    private CurvePointService curvePointService;

    CurvePoint RECORD_1 = new CurvePoint(1, 11.0, 11.0);
    CurvePoint RECORD_2 = new CurvePoint(2, 22.0, 22.0);
    CurvePoint RECORD_3 = new CurvePoint(3, 33.0, 33.0);

    @Test
    @DisplayName("Create a CurvePoint with success")
    public void createARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(curvePointService.saveCurvePoint(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<CurvePoint> re = apiCPController.createCurvePointWithBodyParam(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    @DisplayName("Create a CurvePoint with failing")
    public void createARecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(curvePointService.saveCurvePoint(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<CurvePoint> re = apiCPController.createCurvePointWithBodyParam(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }


    @Test
    @DisplayName("Update a CurvePoint with failing")
    public void putARecord_failing() throws Exception {

        // ARRANGE
        Mockito.when(curvePointService.saveCurvePoint(RECORD_1)).thenReturn(null);
        //ACT
        ResponseEntity<CurvePoint> re = apiCPController.updateCurvePoint(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    @DisplayName("Update a CurvePoint with success")
    public void putARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(curvePointService.saveCurvePoint(RECORD_1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<CurvePoint> re = apiCPController.updateCurvePoint(RECORD_1);
        // ASSERT
        Assert.assertEquals(HttpStatus.OK, re.getStatusCode());

    }
/*
    @Test
    @DisplayName("Delete a CurvePoint with failing")
    public void deleteARecord_failing() throws Exception {
        // ARRANGE
        Mockito.when(curvePointService.findById(1)).thenReturn(null);
        //ACT
        ResponseEntity<Boolean> re = apiCPController.deleteCurvePoint(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }
*/
    @Test
    @DisplayName("Delete a CurvePoint with success")
    public void deleteARecord_success() throws Exception {
        // ARRANGE
        Mockito.when(curvePointService.findById(1)).thenReturn(RECORD_2);
        //ACT
        ResponseEntity<Boolean> re = apiCPController.deleteCurvePoint(1);
        // ASSERT
        Assert.assertEquals(HttpStatus.FOUND, re.getStatusCode());
    }


    @Test
    @DisplayName("Get curvepoint list with success")
    public void getAllRecords_success() throws Exception {
        // ARRANGE
        List<CurvePoint> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<CurvePoint> returnRecords = null;

        Mockito.when(curvePointService.findAllCurvePoints()).thenReturn(records);
        //ACT
        returnRecords = apiCPController.findAllCurvePoints();
        // ASSERT
        Assert.assertNotNull(returnRecords);
    }

    @Test
    @DisplayName("Get curvepoint list with failing")
    public void getAllRecords_failing() throws Exception {
        // ARRANGE
        List<CurvePoint> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        List<CurvePoint> returnedRecords = new ArrayList<>();

        Mockito.when(curvePointService.findAllCurvePoints()).thenReturn(returnedRecords);
        //ACT
        records = apiCPController.findAllCurvePoints();
        // ASSERT
        Assert.assertNull(records);
    }



}
