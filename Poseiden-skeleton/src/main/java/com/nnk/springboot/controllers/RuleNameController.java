package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.impl.RuleNameService;
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
public class RuleNameController {
    public static final Logger logger = LogManager.getLogger(UserController.class);
@Autowired
private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        List<RuleName> ruleNames = ruleNameService.findAllRuleNames();
        logger.info("list rulenames");
        model.addAttribute("ruleNames", ruleNames);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list

        if(ruleName.getName().isBlank()){
            model.addAttribute("errorMsgName", "name est obligatoire");
            return "ruleName/add";
        }

        if (!result.hasErrors()) {
            ruleNameService.saveRuleName(ruleName);
            logger.info("validate and save rulename and display list all rulenames");
            model.addAttribute("ruleNames", ruleNameService.findAllRuleNames());
            return "redirect:/ruleName/list";
        }
        logger.error("cannot validate rulename");
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        RuleName ruleName = ruleNameService.findById(id);  //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        logger.info("Get update rulename by id");
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list

        if(ruleName.getName().isBlank()){
            model.addAttribute("errorMsgName", "name est obligatoire");
            return "ruleName/add";
        }

        if (result.hasErrors()) {
            logger.error("Post update rulename in error");
            return "ruleName/update";
        }
        ruleName.setId(id);

        RuleName ruleName1 = ruleNameService.saveRuleName(ruleName);
        if(ruleName1 == null) {
            logger.error("rulename variable is null");
            return "ruleName/update";
        }

        logger.info("update rulename and display all rulenames");
        model.addAttribute("ruleName", ruleNameService.findAllRuleNames());

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list

        RuleName ruleName = ruleNameService.findById(id);
        //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        ruleNameService.deleteRuleName(ruleName);
        logger.info("delete rulename");
        model.addAttribute("ruleName", ruleNameService.findAllRuleNames());

        return "redirect:/ruleName/list";
    }
}
