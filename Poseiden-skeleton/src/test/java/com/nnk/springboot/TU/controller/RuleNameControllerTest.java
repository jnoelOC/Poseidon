package com.nnk.springboot.TU.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.impl.RuleNameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RuleNameControllerTest {


    @InjectMocks
    private RuleNameController ruleNameController;

    @Mock
    private RuleNameService ruleNameService;

    @Test
    @DisplayName("Get ruleNameList")
    void whenGetRuleName_thenReturnsRuleNameList() throws Exception {
        // ARRANGE
        RuleName rn1 = new RuleName("name1", "descr1", "json1","template1", "Sql1","Part1");
        RuleName rn2 = new RuleName("name2", "descr2", "json2","template2", "Sql2","Part2");
        List<RuleName> rns = new ArrayList<>();
        rns.add(rn1);
        rns.add(rn2);
        when(ruleNameService.findAllRuleNames()).thenReturn(rns);
        // ACT
        String ret = ruleNameController.home(model);
        // ASSERT
        assertThat(ret).hasToString("ruleName/list");
    }

    @Test
    @DisplayName("Get /ruleName/add")
    void whenGetAddRuleName_thenReturnsRuleNamesList() throws Exception {
        // ARRANGE
        RuleName rn1 = new RuleName("name1", "descr1", "json1","template1", "Sql1","Part1");
        // ACT
        String ret = ruleNameController.addRuleForm(rn1);
        // ASSERT
        assertThat(ret).hasToString("ruleName/add");
    }
    @Test
    @DisplayName("Post /ruleName/validate")
    void whenPostValidateRuleName_thenReturnsRuleName() throws Exception {
        // ARRANGE
        RuleName rn1 = new RuleName("name1", "descr1", "json1","template1", "Sql1","Part1");
        RuleName rn2 = new RuleName("name2", "descr2", "json2","template2", "Sql2","Part2");
        List<RuleName> rns = new ArrayList<>();
        rns.add(rn1);
        rns.add(rn2);
        when(ruleNameService.saveRuleName(rn1)).thenReturn(rn2);
        when(ruleNameService.findAllRuleNames()).thenReturn(rns);
        // ACT
        String ret = ruleNameController.validate(rn1, result, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/ruleName/list");
    }

    @Test
    @DisplayName("Post /ruleName/validate with name blank")
    void whenPostValidateRuleNameWithNameBlank_thenReturnsRuleNameAdd() throws Exception {
        // ARRANGE
        RuleName rn1 = new RuleName("", "descr1", "json1","template1", "Sql1","Part1");
        // ACT
        String ret = ruleNameController.validate(rn1, result, model);
        //ASSERT
        assertThat(ret).hasToString("ruleName/add");
    }

    @Test
    @DisplayName("Get /ruleName/Update with id")
    void whenGetUpdate_thenReturnRuleNameUpdate(){
        // ARRANGE
        Integer id = 1;
        RuleName rn1 = new RuleName("name1", "descr1", "json1","template1", "Sql1","Part1");
        when(ruleNameService.findById(id)).thenReturn(rn1);
        // ACT
        String ret = ruleNameController.showUpdateForm(id, model);
        //ASSERT
        assertThat(ret).hasToString("ruleName/update");
    }

    @Test
    @DisplayName("Post /ruleName/Update with id")
    void whenPostUpdate_thenReturnRuleNameUpdate(){
        // ARRANGE
        Integer id = 1;
        RuleName rn1 = new RuleName("name1", "descr1", "json1","template1", "Sql1","Part1");
        RuleName rn2 = new RuleName("name2", "descr2", "json2","template2", "Sql2","Part2");
        List<RuleName> rns = new ArrayList<>();
        rns.add(rn1);
        rns.add(rn2);
        when(ruleNameService.saveRuleName(rn1)).thenReturn(rn1);

        // ACT
        String ret = ruleNameController.updateRuleName(id, rn1, result, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/ruleName/list");
    }

    @Test
    @DisplayName("Post /ruleName/Update with Save returns null")
    void whenPostUpdate_thenReturnRuleNameAtNull(){
        // ARRANGE
        Integer id = 1;
        RuleName rn1 = new RuleName("name1", "descr1", "json1","template1", "Sql1","Part1");

        when(ruleNameService.saveRuleName(rn1)).thenReturn(null);
        // ACT
        String ret = ruleNameController.updateRuleName(id, rn1, result, model);
        //ASSERT
        assertThat(ret).hasToString("ruleName/update");
    }

    @Test
    @DisplayName("Get /ruleName/delete")
    void whenGetDelete_thenReturnRuleName(){
        // ARRANGE
        Integer id = 1;
        RuleName rn1 = new RuleName("name1", "descr1", "json1","template1", "Sql1","Part1");
        when(ruleNameService.findById(id)).thenReturn(rn1);
        // ACT
        String ret = ruleNameController.deleteRuleName(id, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/ruleName/list");
    }






    Model model = new Model() {
    @Override
    public Model addAttribute(String attributeName, Object attributeValue) {
        return null;
    }

    @Override
    public Model addAttribute(Object attributeValue) {
        return null;
    }

    @Override
    public Model addAllAttributes(Collection<?> attributeValues) {
        return null;
    }

    @Override
    public Model addAllAttributes(Map<String, ?> attributes) {
        return null;
    }

    @Override
    public Model mergeAttributes(Map<String, ?> attributes) {
        return null;
    }

    @Override
    public boolean containsAttribute(String attributeName) {
        return false;
    }

    @Override
    public Object getAttribute(String attributeName) {
        return null;
    }

    @Override
    public Map<String, Object> asMap() {
        return null;
    }
};

    BindingResult result = new BindingResult() {
        @Override
        public Object getTarget() {
            return null;
        }

        @Override
        public Map<String, Object> getModel() {
            return null;
        }

        @Override
        public Object getRawFieldValue(String field) {
            return null;
        }

        @Override
        public PropertyEditor findEditor(String field, Class<?> valueType) {
            return null;
        }

        @Override
        public PropertyEditorRegistry getPropertyEditorRegistry() {
            return null;
        }

        @Override
        public String[] resolveMessageCodes(String errorCode) {
            return new String[0];
        }

        @Override
        public String[] resolveMessageCodes(String errorCode, String field) {
            return new String[0];
        }

        @Override
        public void addError(ObjectError error) {

        }

        @Override
        public String getObjectName() {
            return null;
        }

        @Override
        public void setNestedPath(String nestedPath) {

        }

        @Override
        public String getNestedPath() {
            return null;
        }

        @Override
        public void pushNestedPath(String subPath) {

        }

        @Override
        public void popNestedPath() throws IllegalStateException {

        }

        @Override
        public void reject(String errorCode) {

        }

        @Override
        public void reject(String errorCode, String defaultMessage) {

        }

        @Override
        public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {

        }

        @Override
        public void rejectValue(String field, String errorCode) {

        }

        @Override
        public void rejectValue(String field, String errorCode, String defaultMessage) {

        }

        @Override
        public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {

        }

        @Override
        public void addAllErrors(Errors errors) {

        }

        @Override
        public boolean hasErrors() {
            return false;
        }

        @Override
        public int getErrorCount() {
            return 0;
        }

        @Override
        public List<ObjectError> getAllErrors() {
            return null;
        }

        @Override
        public boolean hasGlobalErrors() {
            return false;
        }

        @Override
        public int getGlobalErrorCount() {
            return 0;
        }

        @Override
        public List<ObjectError> getGlobalErrors() {
            return null;
        }

        @Override
        public ObjectError getGlobalError() {
            return null;
        }

        @Override
        public boolean hasFieldErrors() {
            return false;
        }

        @Override
        public int getFieldErrorCount() {
            return 0;
        }

        @Override
        public List<FieldError> getFieldErrors() {
            return null;
        }

        @Override
        public FieldError getFieldError() {
            return null;
        }

        @Override
        public boolean hasFieldErrors(String field) {
            return false;
        }

        @Override
        public int getFieldErrorCount(String field) {
            return 0;
        }

        @Override
        public List<FieldError> getFieldErrors(String field) {
            return null;
        }

        @Override
        public FieldError getFieldError(String field) {
            return null;
        }

        @Override
        public Object getFieldValue(String field) {
            return null;
        }

        @Override
        public Class<?> getFieldType(String field) {
            return null;
        }
    };

}
