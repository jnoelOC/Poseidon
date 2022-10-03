package com.nnk.springboot.TI;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(username = "admin")
public class BidListControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BidListRepository repository;

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
        List<BidList> allBids = repository.findAll();

    }
}