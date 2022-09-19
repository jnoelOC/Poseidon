package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService implements ITradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> findAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade findById(Integer id) {return tradeRepository.findById(id).get(); }

    public Trade saveTrade(Trade trade){ return tradeRepository.save(trade); }

    public void deleteTrade(Trade trade){ tradeRepository.delete(trade); }
}
