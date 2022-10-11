package com.nnk.springboot.TI;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.impl.BidListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(username = "admin")
public class BidListControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BidListService bidListService;


    @Autowired
    BidListRepository bidListRepository;

    @DisplayName("User access and fill add BidList form to add new BidList")
    @Test
    void addBidListIntegrationTest() throws Exception {
        // GIVEN a user requesting the add BidList form

        // WHEN
        mockMvc.perform(get("/bidList/add"))

                // THEN
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"))
                .andExpect(content().string(containsString("Add New Bid")));

        //THEN
        List<BidList> allBids = bidListRepository.findAll();
    }


    @DisplayName("User access and fill list BidList form to list BidList")
    @Test
    void BidListIntegrationTest() throws Exception {
        // GIVEN a user requesting the BidList list
        // WHEN
        mockMvc.perform(get("/bidList/list"))

                // THEN
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(content().string(containsString("Bid List")));

        //THEN
        List<BidList> allBids = bidListRepository.findAll();
    }

    /*
    @DisplayName("User access and fill validate BidList form to validate new BidList")
    @Test
    void validateBidListIntegrationTest() throws Exception {
        // GIVEN a user requesting the validate BidList form
        BidList bid = new BidList("acc111", "typ111", 111.0);

        bid.setType("toto");
        // WHEN
        mockMvc.perform(post("/bidList/validate/{bid}", bid)
                .param("bid", String.valueOf(bid))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept("application/json"))
                // THEN
                .andExpect(status().isOk())
                //contentbidList
                .andExpect(view().name("redirect:/bidList/list"))
                .andExpect(content().string(containsString("Bid List")));

        //THEN
        List<BidList> allBids = bidListRepository.findAll();
          Optional<BidList> result = allBids.stream().filter(bid1 -> bid1.getType().equals("toto")).findAny();
        assertNotNull(result.get());
    }
*/

}