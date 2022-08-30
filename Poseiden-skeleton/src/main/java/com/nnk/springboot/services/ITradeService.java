package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

import java.util.List;

public interface ITradeService {
    List<Trade> findAllTrades() ;

     Trade findById(Integer id);

     Trade saveTrade(Trade trade);

     void deleteTrade(Trade trade);
}
