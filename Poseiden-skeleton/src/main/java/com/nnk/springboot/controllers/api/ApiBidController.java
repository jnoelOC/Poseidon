package com.nnk.springboot.controllers.api;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.IBidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiBidController {
    public static final Logger logger = LogManager.getLogger(ApiBidController.class);

    @Autowired
    IBidListService bidListService;

    @GetMapping("api/bid/list")
    public List<BidList> findAllBids() {

        List<BidList> lbl = bidListService.findAllBids();

        if (lbl.isEmpty()) {
            logger.error("Find all bids : status Non trouvé.");
            return null;
        }
        logger.info("bids trouvés.");
        return lbl;
    }

    @PutMapping("api/bid/update")
    public ResponseEntity<BidList> updateBid(@RequestBody BidList bidList) {

        BidList b = bidListService.saveBidList(bidList);
        if (b == null) {
            logger.error("update bid : status Non trouvé.");
            return new ResponseEntity<>(b, HttpStatus.NOT_FOUND);
        } else {
            logger.info("Update bid : status Ok.");
            return new ResponseEntity<>(b, HttpStatus.OK);
        }
    }

    @DeleteMapping("api/bid/delete")
    public ResponseEntity<Boolean> deleteBid(@RequestParam Integer id) {

		try {
            BidList b = bidListService.findById(id);
            bidListService.deleteBidList(b);
		} catch (Exception ex) {
			logger.error("Error delete bid : "+ ex.getMessage());
            return new ResponseEntity<>(true, HttpStatus.NOT_FOUND);
		}

        logger.info("Delete bid : status Trouvé.");
        return new ResponseEntity<>(true, HttpStatus.FOUND);

    }

    @PostMapping("api/bid/create")
    public ResponseEntity<BidList> createBidWithBodyParam(@RequestBody BidList bidList) {

        BidList b = bidListService.saveBidList(bidList);
        if (b == null) {
            logger.error("create bid : status Non trouvé.");
            return new ResponseEntity<>(b, HttpStatus.NOT_FOUND);
            // throw new PAlreadyCreatedException();
        } else {
            logger.info("Create bid : status Créé.");
            return new ResponseEntity<>(b, HttpStatus.CREATED);
        }

    }


    @GetMapping("api/bid/getOne")
    public ResponseEntity<BidList> findOneBid(@RequestParam Integer id) {

        BidList bid = bidListService.findById(id);
        if (bid == null) {
            logger.error("FindOneBid : status Non trouvé.");
            return new ResponseEntity<>(bid, HttpStatus.NOT_FOUND);
        } else {
            logger.info("status bid trouvé.");
            return new ResponseEntity<>(bid, HttpStatus.FOUND);
        }
    }



}
