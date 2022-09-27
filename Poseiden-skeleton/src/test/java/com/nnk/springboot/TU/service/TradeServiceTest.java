package com.nnk.springboot.TU.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.impl.TradeService;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {


    @InjectMocks
    private TradeService tradeService;

    @Mock
    private TradeRepository tradeRepository;

    @Test
    @DisplayName("find all Trade")
    void FindAllTrades_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        List<Trade> trades = new ArrayList<>();
        Trade trade1 = new Trade("acc1", "typ1");
        Trade trade2 = new Trade("acc2", "typ2");
        trades.add(trade1);
        trades.add(trade2);

        when(tradeRepository.findAll()).thenReturn(trades);
        // Act
        List<Trade> allTrades = tradeService.findAllTrades();
        if (allTrades.isEmpty()) {
            ret = false;
        }

        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("find Trade by id")
    void FindTradeById_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        Optional<Trade> trade3 = Optional.of(new Trade("acc3", "typ3"));
        trade3.ifPresent( ratingValue ->
                when(tradeRepository.findById(1)).thenReturn(trade3)
        );
        // Act
        Trade trade2 = tradeService.findById(1);
        if (trade2 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("save Trade")
    void SaveTrade_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        Trade tr1 = new Trade("acc1", "typ1");
        Trade tr3 = new Trade("acc3", "typ3");

        when(tradeRepository.save(tr3)).thenReturn(tr1);
        // Act
        Trade tr2 = tradeService.saveTrade(tr3);
        if (tr2 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("delete Trade")
    void DeleteTrade_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        Trade tr3 = new Trade("acc3", "typ3");
        tradeRepository.delete(Mockito.any(Trade.class));
        // Act
        tradeService.deleteTrade(tr3);
        // Assert
        assertTrue(ret);
    }
}
