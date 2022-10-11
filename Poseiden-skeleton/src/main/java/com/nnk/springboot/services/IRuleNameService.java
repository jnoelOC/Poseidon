package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRuleNameService {

    List<RuleName> findAllRuleNames();

    RuleName findById(Integer id) ;

    RuleName saveRuleName(RuleName ruleName);

    void deleteRuleName(RuleName ruleName);
}
