package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.impl.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class BidListController {
    // TODO: Inject Bid service

    public static final Logger logger = LogManager.getLogger(BidListController.class);

    @Autowired
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        List<BidList> bids = bidListService.findAllBids();
        model.addAttribute("bids", bids);
        logger.info("Requête de Read de BidList");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list

        if(bid.getAccount().isBlank()){
            model.addAttribute("errorMsgAccount", "Account is mandatory");
            return "bidList/add";
        }
        if(bid.getType().isBlank()){
            model.addAttribute("errorMsgType", "Type is mandatory");
            return "bidList/add";
        }

        if (!result.hasErrors()) {
//            bid.setAccount("account1");
//            bid.setType("type1");
//            bid.setBidQuantity(11D);
            bidListService.saveBidList(bid);
            model.addAttribute("bids", bidListService.findAllBids());
            logger.info("Requête de Add de BidList");
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        BidList bidList = bidListService.findById(id);  //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid

        if(bidList.getAccount().isBlank()){
            model.addAttribute("errorMsgAccount", "Account is mandatory");
            return "bidList/update";
        }
        if(bidList.getType().isBlank()){
            model.addAttribute("errorMsgType", "Type is mandatory");
            return "bidList/update";
        }

        if (result.hasErrors()) {
            return "bidList/update";
        }
       bidList.setBidListId(id);

        BidList bidList1 = bidListService.saveBidList(bidList);
        if(bidList1 == null) {
            return "bidList/update";
        }
        model.addAttribute("bidList", bidListService.findAllBids());
        logger.info("Requête de Update de BidList");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        try {
            BidList bidList = bidListService.findById(id);
            //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            bidListService.deleteBidList(bidList);
            logger.info("Requête de Delete de BidList");
            model.addAttribute("bidList", bidListService.findAllBids());
        }
        catch(Exception ex){

        }
        return "redirect:/bidList/list";
    }
}
