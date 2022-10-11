package com.nnk.springboot.controllers.api;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.IRatingService;
import com.nnk.springboot.services.IRuleNameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiRuleNameController {

    public static final Logger logger = LogManager.getLogger(ApiRuleNameController.class);

    @Autowired
    IRuleNameService ruleNameService;

    @GetMapping("api/rn/list")
    public List<RuleName> findAllRuleNames() {

        List<RuleName> lrn = ruleNameService.findAllRuleNames();

        if (lrn.isEmpty()) {
            logger.error("Find all RuleNames : status Non trouvé.");
            return null;
        }
        logger.info("RuleNames trouvés.");
        return lrn;
    }

    @PutMapping("api/rn/update")
    public ResponseEntity<RuleName> updateRuleName(@RequestBody RuleName ruleName) {

        RuleName rn = ruleNameService.saveRuleName(ruleName);
        if (rn == null) {
            logger.error("update RuleName : status Non trouvé.");
            return new ResponseEntity<>(rn, HttpStatus.NOT_FOUND);
        } else {
            logger.info("Update RuleName : status Ok.");
            return new ResponseEntity<>(rn, HttpStatus.OK);
        }
    }

    @DeleteMapping("api/rn/delete")
    public ResponseEntity<Boolean> deleteRuleName(@RequestParam Integer id) {

        try {
            RuleName rn = ruleNameService.findById(id);
            ruleNameService.deleteRuleName(rn);
        } catch (Exception ex) {
            logger.error("Error delete RuleName : "+ ex.getMessage());
            return new ResponseEntity<>(true, HttpStatus.NOT_FOUND);
        }

        logger.info("Delete RuleName : status Trouvé.");
        return new ResponseEntity<>(true, HttpStatus.FOUND);

    }

    @PostMapping("api/rn/create")
    public ResponseEntity<RuleName> createRuleNameWithBodyParam(@RequestBody RuleName ruleName) {

        RuleName rn = ruleNameService.saveRuleName(ruleName);
        if (rn == null) {
            logger.error("create RuleName : status Non trouvé.");
            return new ResponseEntity<>(rn, HttpStatus.NOT_FOUND);
        } else {
            logger.info("Create RuleName : status Créé.");
            return new ResponseEntity<>(rn, HttpStatus.CREATED);
        }

    }


    @GetMapping("api/rn/getOne")
    public ResponseEntity<RuleName> findOneRuleName(@RequestParam Integer id) {

        RuleName rn = ruleNameService.findById(id);
        if (rn == null) {
            logger.error("FindOneRuleName : status Non trouvé.");
            return new ResponseEntity<>(rn, HttpStatus.NOT_FOUND);
        } else {
            logger.info("status RuleName trouvé.");
            return new ResponseEntity<>(rn, HttpStatus.FOUND);
        }
    }
}
