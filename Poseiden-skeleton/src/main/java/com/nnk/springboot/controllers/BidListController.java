package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.impl.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static final Logger logger = LogManager.getLogger(BidListController.class);

    @Autowired
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        List<BidList> bids = bidListService.findAllBids();
        model.addAttribute("bids", bids);
        logger.info("Requête de Read de BidList");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("Requête (Get) de Add de BidList");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {

        if(bid.getAccount().isBlank()){
            model.addAttribute("errorMsgAccount", "Account est obligatoire");
            return "bidList/add";
        }

        if(bid.getType().isBlank()){
            model.addAttribute("errorMsgType", "Type est obligatoire");
            return "bidList/add";
        }

        try {
            if (bid.getBidQuantity().isNaN()) {
                model.addAttribute("errorMsgBidQty", "BidQuantity est obligatoire et doit être un nombre");
                return "bidList/add";
            }
        }catch(Exception ex){
            logger.error(ex.getMessage());
            model.addAttribute("errorMsgBidQty", "BidQuantity est obligatoire et doit être un nombre");
            return "bidList/add";
        }

        if (!result.hasErrors()) {
            bidListService.saveBidList(bid);
            model.addAttribute("bids", bidListService.findAllBids());
            logger.info("Requête (Post) de Add de BidList");
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        BidList bidList = bidListService.findById(id);

        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {

        if(bidList.getAccount().isBlank()){
            model.addAttribute("errorMsgAccount", "Account est obligatoire");
            return "bidList/update";
        }
        if(bidList.getType().isBlank()){
            model.addAttribute("errorMsgType", "Type est obligatoire");
            return "bidList/update";
        }

        try {
            if (bidList.getBidQuantity().isNaN()) {
                model.addAttribute("errorMsgBidQty", "BidQuantity est obligatoire et doit être un nombre");
                return "bidList/update";
            }
        }catch(Exception ex){
            logger.error(ex.getMessage());
            model.addAttribute("errorMsgBidQty", "BidQuantity est obligatoire et doit être un nombre");
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

            BidList bidList = bidListService.findById(id);
            bidListService.deleteBidList(bidList);
            logger.info("Requête de Delete de BidList");
            model.addAttribute("bidList", bidListService.findAllBids());
        return "redirect:/bidList/list";
    }
}
