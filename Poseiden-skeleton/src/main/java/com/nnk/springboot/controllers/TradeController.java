package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.impl.TradeService;
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
public class TradeController {
    public static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
        List<Trade> trades = tradeService.findAllTrades();
        logger.info("List all trades");
        model.addAttribute("trades", trades);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list


        if(trade.getAccount().isBlank()){
            model.addAttribute("errorMsgAccount", "Account est obligatoire");
            return "trade/add";
        }
        if(trade.getType().isBlank()){
            model.addAttribute("errorMsgType", "Type est obligatoire");
            return "trade/add";
        }
        try {
            if (trade.getBuyQuantity().isNaN()) {
                model.addAttribute("errorMsgBuyQty", "BuyQuantity est obligatoire");
                return "trade/add";
            }
        }catch(Exception ex){
            logger.error(ex.getMessage());
            model.addAttribute("errorMsgBuyQty", "BuyQuantity est obligatoire");
            return "trade/add";
        }

        if (!result.hasErrors()) {
            tradeService.saveTrade(trade);
            logger.info("Post validate trade");
            model.addAttribute("trades", tradeService.findAllTrades());
            return "redirect:/trade/list";
        }

        logger.error("Post validate trade in error");
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form

        Trade trade = tradeService.findById(id);  //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        logger.info("Get update trade by id");
        model.addAttribute("trade", trade);

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list

        if(trade.getAccount().isBlank()){
            model.addAttribute("errorMsgAccount", "Account est obligatoire");
            return "trade/add";
        }
        if(trade.getType().isBlank()){
            model.addAttribute("errorMsgType", "Type est obligatoire");
            return "trade/add";
        }

        if (result.hasErrors()) {
            logger.error("Post update trade in error");
            return "trade/update";
        }
        trade.setTradeId(id);

        Trade trade1 = tradeService.saveTrade(trade);
        if(trade1 == null) {
            logger.error("trade variable is null");
            return "trade/update";
        }
        model.addAttribute("trade", tradeService.findAllTrades());

        logger.info("Post update trade and display all trades in list");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list

        Trade trade = tradeService.findById(id);
        tradeService.deleteTrade(trade);
        logger.info("delete trade by id");
        model.addAttribute("trade", tradeService.findAllTrades());

        return "redirect:/trade/list";
    }
}
