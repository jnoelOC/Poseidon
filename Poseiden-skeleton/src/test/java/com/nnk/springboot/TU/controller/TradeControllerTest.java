package com.nnk.springboot.TU.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.impl.TradeService;
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
public class TradeControllerTest {

    @InjectMocks
    private TradeController tradeController;

    @Mock
    private TradeService tradeService;

    @Test
    @DisplayName("Get trade")
    void whenGetTradeList_thenReturnsTradeList() throws Exception {
        // ARRANGE
        Trade tr1 = new Trade("acc1", "typ1");
        Trade tr2 = new Trade("acc2", "typ2");
        List<Trade> trades = new ArrayList<>();
        trades.add(tr1);
        trades.add(tr2);
        when(tradeService.findAllTrades()).thenReturn(trades);
        // ACT
        String ret = tradeController.home(model);
        // ASSERT
        assertThat(ret).hasToString("trade/list");
    }

    @Test
    @DisplayName("Get /trade/add")
    void whenGetAddTrade_thenReturnsTradeList() throws Exception {
        // ARRANGE
        Trade tr1 = new Trade("acc1", "typ1");
        // ACT
        String ret = tradeController.addTrade(tr1);
        // ASSERT
        assertThat(ret).hasToString("trade/add");
    }

    @Test
    @DisplayName("Post /trade/validate")
    void whenPostValidateTrade_thenReturnsTradeList() throws Exception {
        // ARRANGE
        Trade tr1 = new Trade("acc1", "typ1");
        tr1.setBuyQuantity(12.0);
        Trade tr2 = new Trade("acc2", "typ2");
        Trade tr3 = new Trade("acc3", "typ3");
        List<Trade> trades = new ArrayList<>();
        trades.add(tr1);        trades.add(tr2);    trades.add(tr3);
        when(tradeService.saveTrade(tr1)).thenReturn(tr2);
        when(tradeService.findAllTrades()).thenReturn(trades);
        // ACT
        String ret = tradeController.validate(tr1, result, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/trade/list");
    }

    @Test
    @DisplayName("Post /trade/validate with Account blank")
    void whenPostValidateTradeWithAccountBlank_thenReturnsTradeAdd() throws Exception {
        // ARRANGE
        Trade tr1 = new Trade("", "typ1");
        tr1.setBuyQuantity(12.0);
        // ACT
        String ret = tradeController.validate(tr1, result, model);
        //ASSERT
        assertThat(ret).hasToString("trade/add");
    }

    @Test
    @DisplayName("Post /trade/validate with Type blank")
    void whenPostValidateTradeWithTypeBlank_thenReturnsTradeAdd() throws Exception {
        // ARRANGE
        Trade tr1 = new Trade("Account1", "");
        tr1.setBuyQuantity(12.0);
        // ACT
        String ret = tradeController.validate(tr1, result, model);
        //ASSERT
        assertThat(ret).hasToString("trade/add");
    }

    @Test
    @DisplayName("Post /trade/validate with BuyQty at null")
    void whenPostValidateTradeWithBuyQtyAtNull_thenReturnsTradeAdd() throws Exception {
        // ARRANGE
        Trade tr1 = new Trade("Account1", "Type1");
        tr1.setBuyQuantity(null);
        // ACT
        String ret = tradeController.validate(tr1, result, model);
        //ASSERT
        assertThat(ret).hasToString("trade/add");
    }

    @Test
    @DisplayName("Get /trade/Update with id")
    void whenGetUpdate_thenReturnTradeUpdate(){
        // ARRANGE
        Integer id = 1;
        Trade tr1 = new Trade("Account1", "Type1");
        tr1.setBuyQuantity(123.0);
        when(tradeService.findById(id)).thenReturn(tr1);
        // ACT
        String ret = tradeController.showUpdateForm(id, model);
        //ASSERT
        assertThat(ret).hasToString("trade/update");
    }

    @Test
    @DisplayName("Post /trade/Update with id")
    void whenPostUpdate_thenReturnTradeUpdate(){
        // ARRANGE
        Integer id = 1;
        Trade tr1 = new Trade("Account1", "Type1");
        tr1.setBuyQuantity(123.0);
        Trade tr2 = new Trade("Account2", "Type2");
        List<Trade> trades = new ArrayList<>();
        trades.add(tr1);
        trades.add(tr2);
        when(tradeService.saveTrade(tr1)).thenReturn(tr2);
        // ACT
        String ret = tradeController.updateTrade(id, tr1, result, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/trade/list");
    }

    @Test
    @DisplayName("Post /trade/Update with Save returns null")
    void whenPostUpdate_thenReturnTradeAtNull(){
        // ARRANGE
        Integer id = 1;
        Trade tr1 = new Trade("Account1", "Type1");
        tr1.setBuyQuantity(123.0);
        Trade tr2 = new Trade("Account2", "Type2");
        List<Trade> trades = new ArrayList<>();
        trades.add(tr1);
        trades.add(tr2);
        when(tradeService.saveTrade(tr1)).thenReturn(null);
        // ACT
        String ret = tradeController.updateTrade(id, tr1, result, model);
        //ASSERT
        assertThat(ret).hasToString("trade/add");
    }

    @Test
    @DisplayName("Post /bidList/Update with Account blank")
    void whenPostUpdate_thenReturnBidListAccountBlank(){
        // ARRANGE
        Integer id = 1;
        Trade tr1 = new Trade("", "Type1");
        tr1.setBuyQuantity(123.0);
        // ACT
        String ret = tradeController.updateTrade(id, tr1, result, model);
        //ASSERT
        assertThat(ret).hasToString("trade/add");
    }

    @Test
    @DisplayName("Post /bidList/Update with Type blank")
    void whenPostUpdate_thenReturnBidListTypeBlank(){
        // ARRANGE
        Integer id = 1;
        Trade tr1 = new Trade("Account1", "");
        tr1.setBuyQuantity(123.0);
        // ACT
        String ret = tradeController.updateTrade(id, tr1, result, model);
        //ASSERT
        assertThat(ret).hasToString("trade/add");
    }

    @Test
    @DisplayName("Get /trade/delete")
    void whenGetDelete_thenReturnTrade(){
        // ARRANGE
        Integer id = 1;
        Trade tr1 = new Trade("Account1", "type1");
        tr1.setBuyQuantity(123.0);
        when(tradeService.findById(id)).thenReturn(tr1);
        // ACT
        String ret = tradeController.deleteTrade(id, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/trade/list");
    }









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
}
