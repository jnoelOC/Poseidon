package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.IBidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService implements IBidListService {

    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> findAllBids() {
        return bidListRepository.findAll();
    }

    public BidList findById(Integer id) {return bidListRepository.findById(id).get(); }

    public BidList saveBidList(BidList bid){ return bidListRepository.save(bid); }

    public void deleteBidList(BidList bid){ bidListRepository.delete(bid); }
}
