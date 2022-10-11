package com.nnk.springboot.TI;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.util.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
@WithMockUser(username = "admin")
public class ApiBidControllerIT {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    BidListRepository bidListRepository;


    BidList RECORD_1 = new BidList("Acct1", "Typ1", 1.0);
    BidList RECORD_2 = new BidList("Acct2", "Typ2", 2.0);
    BidList RECORD_3 = new BidList("Acct3", "Typ3", 3.0);
/*
    @Test
    public void getAllRecords_success() throws Exception {
        List<BidList> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(bidListRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/bid/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$", hasSize(3)))
        // .andExpect((ResultMatcher) jsonPath("$[2].account", is("Acct3")));
    }

    @Test
    public void getAllRecords_failing() throws Exception {
        List<BidList> records = new ArrayList<>(Arrays.asList());

        Mockito.when(bidListRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/bid/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Create a bid with success")
    public void createARecord_success() throws Exception {

        Mockito.when(bidListRepository.save(RECORD_3)).thenReturn(RECORD_3);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/bid/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.asString(RECORD_3)))
                .andExpect(status().isOk());
        //       Assert.assertNotNull();
    }

    @Test
    public void createARecord_failing() throws Exception {
        Mockito.when(bidListRepository.save(RECORD_3)).thenReturn(RECORD_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/bid/create")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void putARecord_failing() throws Exception {

        Mockito.when(bidListRepository.save(RECORD_1)).thenReturn(RECORD_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/bid/update/RECORD_3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putARecord_success() throws Exception {

        Mockito.when(bidListRepository.save(RECORD_3)).thenReturn(RECORD_3);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/bid/update/RECORD_3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteARecord_failing() throws Exception {

        Mockito.when(bidListRepository.findById(RECORD_1.getBidListId())).thenReturn(Optional.ofNullable(RECORD_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/bid/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteARecord_success() throws Exception {

        Mockito.when(bidListRepository.findById(RECORD_3.getBidListId())).thenReturn(Optional.ofNullable(RECORD_3));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/bid/delete/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
*/

}
