package com.nnk.springboot.TU.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.impl.BidListService;
import com.nnk.springboot.services.impl.MyUserDetailsService;
import com.nnk.springboot.services.impl.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BidListControllerTest {

    @InjectMocks
    private BidListController bidListController;

    @Mock
    private BidListService bidListService;
/*
    @Mock
    private MyUserDetailsService myUserDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserService userService;
*/

    @Test
    @DisplayName("Get bidList")
    void whenLoginInEntry_thenReturnsBidList() throws Exception {
        // ARRANGE
        Model model = null;
        BidList bid1 = new BidList("acc1", "typ1", 11.0);
        BidList bid2 = new BidList("acc2", "typ2", 22.0);
        List<BidList> bids = new ArrayList<>();
        bids.add(bid1);
        bids.add(bid2);
        when(bidListService.findAllBids()).thenReturn(bids);

        model.addAttribute("bids", bids);
        // ACT
        String ret = bidListController.home(model);
        // ASSERT
        assertThat(ret).hasToString("bidList/list");

    }



}
