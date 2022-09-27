package com.nnk.springboot.TU.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.impl.RuleNameService;
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
public class RuleNameServiceTest {


    @InjectMocks
    private RuleNameService ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    @Test
    @DisplayName("find all RuleNames")
    void FindAllRuleNames_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        List<RuleName> ruleNames = new ArrayList<>();
        RuleName rn1 = new RuleName("name1", "descr1", "json1", "templ1", "sql1","sqlP1");
        RuleName rn2 = new RuleName("name2", "descr2", "json2", "templ2", "sql2","sqlP2");
        ruleNames.add(rn1);
        ruleNames.add(rn2);

        when(ruleNameRepository.findAll()).thenReturn(ruleNames);
        // Act
        List<RuleName> allRuleNames = ruleNameService.findAllRuleNames();
        if (allRuleNames.isEmpty()) {
            ret = false;
        }

        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("find RuleName by id")
    void FindRuleNameById_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        Optional<RuleName> rn3 = Optional.of(new RuleName("name3", "descr3", "json3", "templ3", "sql3","sqlP3"));
        rn3.ifPresent( ratingValue ->
                when(ruleNameRepository.findById(1)).thenReturn(rn3)
        );
        // Act
        RuleName rn2 = ruleNameService.findById(1);
        if (rn2 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("save RuleName")
    void SaveRuleName_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        RuleName rn1 = new RuleName("name1", "descr1", "json1", "templ1", "sql1","sqlP1");
        RuleName rn3 = new RuleName("name3", "descr3", "json3", "templ3", "sql3","sqlP3");

        when(ruleNameRepository.save(rn3)).thenReturn(rn1);
        // Act
        RuleName rn2 = ruleNameService.saveRuleName(rn3);
        if (rn2 == null) {
            ret = false;
        }
        // Assert
        assertTrue(ret);
    }

    @Test
    @DisplayName("delete RuleName")
    void DeleteRuleName_ShouldReturnTrue() {
        // Arrange
        Boolean ret = true;
        RuleName rn3 = new RuleName("name3", "descr3", "json3", "templ3", "sql3","sqlP3");
        ruleNameRepository.delete(Mockito.any(RuleName.class));
        // Act
        ruleNameService.deleteRuleName(rn3);
        // Assert
        assertTrue(ret);
    }

}
