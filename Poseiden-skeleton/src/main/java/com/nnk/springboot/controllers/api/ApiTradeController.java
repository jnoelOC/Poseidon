package com.nnk.springboot.controllers.api;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.IBidListService;
import com.nnk.springboot.services.ITradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiTradeController {

    public static final Logger logger = LogManager.getLogger(ApiTradeController.class);

    @Autowired
    ITradeService tradeService;

    @GetMapping("api/trade/list")
    public List<Trade> findAllTrades() {

        List<Trade> lt = tradeService.findAllTrades();

        if (lt.isEmpty()) {
            logger.error("Find all Trades : status Non trouvé.");
            return null;
        }
        logger.info("Trades trouvés.");
        return lt;
    }

    @PutMapping("api/trade/update")
    public ResponseEntity<Trade> updateTrade(@RequestBody Trade trade) {

        Trade t = tradeService.saveTrade(trade);
        if (t == null) {
            logger.error("update Trade : status Non trouvé.");
            return new ResponseEntity<>(t, HttpStatus.NOT_FOUND);
        } else {
            logger.info("Update Trade : status Ok.");
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
    }

    @DeleteMapping("api/trade/delete")
    public ResponseEntity<Boolean> deleteTrade(@RequestParam Integer id) {

        try {
            Trade t = tradeService.findById(id);
            tradeService.deleteTrade(t);
        } catch (Exception ex) {
            logger.error("Error delete Trade : "+ ex.getMessage());
            return new ResponseEntity<>(true, HttpStatus.NOT_FOUND);
        }

        logger.info("Delete Trade : status Trouvé.");
        return new ResponseEntity<>(true, HttpStatus.FOUND);

    }

    @PostMapping("api/trade/create")
    public ResponseEntity<Trade> createTradeWithBodyParam(@RequestBody Trade trade) {

        Trade t = tradeService.saveTrade(trade);
        if (t == null) {
            logger.error("create Trade : status Non trouvé.");
            return new ResponseEntity<>(t, HttpStatus.NOT_FOUND);
            // throw new PAlreadyCreatedException();
        } else {
            logger.info("Create Trade : status Créé.");
            return new ResponseEntity<>(t, HttpStatus.CREATED);
        }

    }


    @GetMapping("api/trade/getOne")
    public ResponseEntity<Trade> findOneBid(@RequestParam Integer id) {

        Trade t = tradeService.findById(id);
        if (t == null) {
            logger.error("FindOneTrade : status Non trouvé.");
            return new ResponseEntity<>(t, HttpStatus.NOT_FOUND);
        } else {
            logger.info("status Trade trouvé.");
            return new ResponseEntity<>(t, HttpStatus.FOUND);
        }
    }

}
