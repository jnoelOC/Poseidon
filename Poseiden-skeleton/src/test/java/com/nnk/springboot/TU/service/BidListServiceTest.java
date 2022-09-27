package com.nnk.springboot.TU.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.impl.BidListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {
    @InjectMocks
    private BidListService bidListService;

    @Mock
    private BidListRepository bidListRepository;

    @Test
    @DisplayName("find all bids")
    void FindAllBids_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        List<BidList> bids = new ArrayList<>();
        BidList bid1 = new BidList("acc1","typ1",10.0);
        BidList bid2 = new BidList("acc2","typ2",22.0);
        bids.add(bid1);
        bids.add(bid2);

        when(bidListRepository.findAll()).thenReturn(bids);
        // Act
        List<BidList> allBids = bidListService.findAllBids();
        if (allBids.isEmpty()) {
            ret = false;
        }

        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("find bid by id")
    void FindBidById_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        Optional<BidList> bid1 = Optional.of(new BidList("acc3", "typ3", 33.0));

        bid1.ifPresent( bidValue ->
                when(bidListRepository.findById(1)).thenReturn(bid1)
                );

        // Act
        BidList bid2 = bidListService.findById(1);
        if (bid2 == null) {
            ret = false;
        }

        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("save bid")
    void SaveBid_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        BidList bid1 = new BidList("acc1", "typ1", 11.0);
        BidList bid3 = new BidList("acc3", "typ3", 33.0);

        when(bidListRepository.save(bid3)).thenReturn(bid1);
        // Act
        BidList bid2 = bidListService.saveBidList(bid3);
        if (bid2 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("delete bid")
    void DeleteBid_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        BidList bid3 = new BidList("acc3", "typ3", 33.0);
        bidListRepository.delete(Mockito.any(BidList.class));
        // Act
        bidListService.deleteBidList(bid3);
        // Assert
        assertTrue(ret);
    }
}
