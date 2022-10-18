package com.nnk.springboot.TI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.impl.BidListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.netbeans.lib.cvsclient.commandLine.command.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.matchesRegex;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @DisplayName("User access and fill list BidList form to list BidList")
    @Test
    void BidListIntegrationTest() throws Exception {
        // GIVEN a user requesting the BidList list
        // WHEN
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(content().string(containsString("Bid List")));

        //THEN
        List<BidList> allBids = bidListRepository.findAll();
        assertNotNull(allBids);
    }

    @DisplayName("User access and fill add BidList form to add new BidList")
    @Test
    void addBidListIntegrationTest() throws Exception {
        // GIVEN a user requesting the add BidList form
        // WHEN
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"))
                .andExpect(content().string(containsString("Add New Bid")));

        //THEN
        Optional<BidList> bid = bidListRepository.findById(1);
        if(bid.isPresent()) {
            assertNotNull(bid);
        }
        else {
            assertNotNull(bid);
        }
    }
/*
    @DisplayName("User access and fill validate BidList form to validate new BidList")
    @Test
    void validateBidListIntegrationTest() throws Exception {
        // GIVEN a user requesting the validate BidList form
        BidList bid = new BidList("acc111", "typ111", 111.0);
        bid.setType("t1");

        // WHEN
        MockHttpServletResponse  response = mockMvc.perform(
                        post("/bidList/validate/{bid}/", bid)
//               .param("bid", String.valueOf(bid))
                    .content(asJsonString(bid))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept("application/json")
                )
                .andDo(print())
                .andReturn().getResponse();

        //THEN
       // assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.is2xxSuccessful()));
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @DisplayName("User access and fill validate BidList form to validate new BidList")
    @Test
    void validate2BidListIntegrationTest() throws Exception {
        // GIVEN a user requesting the validate BidList form
        BidList bid = new BidList("acc111", "typ111", 111.0);
        bid.setType("t1");
        ResultMatcher matcher = null;

        // WHEN
         ResultActions res = mockMvc.perform(
//                         post("/bidList/validate")
                 post("/bidList/validate/{bid}/", bid)
//               .param("bid", String.valueOf(bid))
         //               .content(asJsonString(bid))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                                //.content("{ "account": "acc111", "type": "typ111", "bidQuatity": 5000.0 }")

                        .accept("application/json")
                )
                .andDo(print())
//                 .andExpect(jsonPath("$.*", isA(ArrayList.class)))
//                 .andExpect((ResultMatcher) jsonPath("$[0].account", is("acc111")))
//                 .andExpect((ResultMatcher) jsonPath("$[0].type", is("t1")))
//                 .andExpect((ResultMatcher) jsonPath("$[0].bidQuantity", is(111.0)))
         //       .andExpect(jsonPath("$.account", is("acc111")))
         //       .andExpect(jsonPath("$[0].created", is("2019-03-01"))).


                .andExpect(status().isOk())
                //contentbidList
                .andExpect(view().name("redirect:/bidList/list"))
                .andExpect(content().string(containsString("Bid List")));

         res.andExpect(matcher);
        //THEN
        List<BidList> allBids = bidListRepository.findAll();
        Optional<BidList> result = allBids.stream().filter(bid1 -> bid1.getType().equals("t1")).findAny();
        assertNotNull(result.get());
    }
*/
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}