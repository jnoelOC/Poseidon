package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IBidListService {
    List<BidList> findAllBids();
    BidList saveBidList(BidList bid);
    BidList findById(Integer id);
    void deleteBidList(BidList bid);
}
