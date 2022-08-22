package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.IRuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService implements IRuleNameService {
    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> findAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    public RuleName findById(Integer id) {return ruleNameRepository.findById(id).get(); }

    public RuleName saveRuleName(RuleName ruleName){ return ruleNameRepository.save(ruleName); }

    public void deleteRuleName(RuleName ruleName){ ruleNameRepository.delete(ruleName); }
}
