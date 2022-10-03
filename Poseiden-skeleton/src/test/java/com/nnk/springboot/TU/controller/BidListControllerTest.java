package com.nnk.springboot.TU.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.impl.BidListService;
import com.nnk.springboot.services.impl.MyUserDetailsService;
import com.nnk.springboot.services.impl.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BidListControllerTest {

    @InjectMocks
    private BidListController bidListController;

    @Mock
    private BidListService bidListService;
/*
    @Mock
    private MyUserDetailsService myUserDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserService userService;
*/

    @Test
    @DisplayName("Get bidList")
    void whenGetBidList_thenReturnsBidList() throws Exception {
        // ARRANGE
        BidList bid1 = new BidList("acc1", "typ1", 111.0);
        BidList bid2 = new BidList("acc2", "typ2", 222.0);
        List<BidList> bids = new ArrayList<>();
        bids.add(bid1);
        bids.add(bid2);
        when(bidListService.findAllBids()).thenReturn(bids);
        // ACT
        String ret = bidListController.home(model);
        // ASSERT
        assertThat(ret).hasToString("bidList/list");
    }

    @Test
    @DisplayName("Get /bidList/add")
    void whenGetAddBidList_thenReturnsBidList() throws Exception {
        // ARRANGE
        BidList bid = new BidList("account1", "type1", 111.0);
        // ACT
        String ret = bidListController.addBidForm(bid);
        // ASSERT
        assertThat(ret).hasToString("bidList/add");
    }

    @Test
    @DisplayName("Post /bidList/validate")
    void whenPostValidateBidList_thenReturnsBidList() throws Exception {
        // ARRANGE
        BidList bid0 = new BidList("account0", "type0",  333.0);
        BidList bid1 = new BidList("acc1", "typ1", 111.0);
        BidList bid2 = new BidList("acc2", "typ2", 222.0);
        List<BidList> bids = new ArrayList<>();
        bids.add(bid1);
        bids.add(bid2);
        when(bidListService.saveBidList(bid0)).thenReturn(bid0);
        when(bidListService.findAllBids()).thenReturn(bids);
        // ACT
        String ret = bidListController.validate(bid0, result, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/bidList/list");
    }

    @Test
    @DisplayName("Post /bidList/validate with Account blank")
    void whenPostValidateBidListWithAccountBlank_thenReturnsBidListAdd() throws Exception {
        // ARRANGE
        BidList bid0 = new BidList("", "type0",  333.0);
        // ACT
        String ret = bidListController.validate(bid0, result, model);
        //ASSERT
        assertThat(ret).hasToString("bidList/add");
    }

    @Test
    @DisplayName("Post /bidList/validate with Type blank")
    void whenPostValidateBidListWithTypeBlank_thenReturnsBidListAdd() throws Exception {
        // ARRANGE
        BidList bid0 = new BidList("Account0", "",  333.0);
        // ACT
        String ret = bidListController.validate(bid0, result, model);
        //ASSERT
        assertThat(ret).hasToString("bidList/add");
    }

    @Test
    @DisplayName("Post /bidList/validate with bidQty null")
    void whenPostValidateBidListWithBidQtyNull_thenReturnsBidListAdd() throws Exception {
        // ARRANGE
        BidList bid0 = new BidList("Account0", "Type0",  null);
        // ACT
        String ret = bidListController.validate(bid0, result, model);
        //ASSERT
        assertThat(ret).hasToString("bidList/add");
    }

     @Test
     @DisplayName("Get /bidList/Update with id")
     void whenGetUpdate_thenReturnBidListUpdate(){
         // ARRANGE
         Integer id = 1;
         BidList bid0 = new BidList("account0", "type0",  333.0);
         when(bidListService.findById(id)).thenReturn(bid0);
         // ACT
         String ret = bidListController.showUpdateForm(id, model);
         //ASSERT
         assertThat(ret).hasToString("bidList/update");
     }

    @Test
    @DisplayName("Post /bidList/Update with id")
    void whenPostUpdate_thenReturnBidListUpdate(){
        // ARRANGE
        Integer id = 1;
        BidList bid0 = new BidList("account0", "type0",  333.0);
        BidList bid1 = new BidList("acc1", "typ1", 111.0);
        BidList bid2 = new BidList("acc2", "typ2", 222.0);
        List<BidList> bids = new ArrayList<>();
        bids.add(bid1);
        bids.add(bid2);
        when(bidListService.saveBidList(bid0)).thenReturn(bid1);
        //when(bidListService.findAllBids()).thenReturn(bids);
        // ACT
        String ret = bidListController.updateBid(id, bid0, result, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/bidList/list");
    }

    @Test
    @DisplayName("Post /bidList/Update with Save returns null")
    void whenPostUpdate_thenReturnBidListAtNull(){
        // ARRANGE
        Integer id = 1;
        BidList bid0 = new BidList("account0", "type0",  333.0);
        BidList bid1 = new BidList("acc1", "typ1", 111.0);
        BidList bid2 = new BidList("acc2", "typ2", 222.0);
        List<BidList> bids = new ArrayList<>();
        bids.add(bid1);
        bids.add(bid2);
        when(bidListService.saveBidList(bid0)).thenReturn(null);
        // ACT
        String ret = bidListController.updateBid(id, bid0, result, model);
        //ASSERT
        assertThat(ret).hasToString("bidList/update");
    }

    @Test
    @DisplayName("Post /bidList/Update with Account blank")
    void whenPostUpdate_thenReturnBidListAccountAtNull(){
        // ARRANGE
        Integer id = 1;
        BidList bid0 = new BidList("", "type0",  333.0);
        // ACT
        String ret = bidListController.updateBid(id, bid0, result, model);
        //ASSERT
        assertThat(ret).hasToString("bidList/update");
    }

    @Test
    @DisplayName("Post /bidList/Update with Type blank")
    void whenPostUpdate_thenReturnBidListTypeAtNull(){
        // ARRANGE
        Integer id = 1;
        BidList bid0 = new BidList("Account0", "",  333.0);
        // ACT
        String ret = bidListController.updateBid(id, bid0, result, model);
        //ASSERT
        assertThat(ret).hasToString("bidList/update");
    }

    @Test
    @DisplayName("Post /bidList/Update with bidQty at null")
    void whenPostUpdate_thenReturnBidListBidQtyAtNull(){
        // ARRANGE
        Integer id = 1;
        BidList bid0 = new BidList("account0", "type0",  null);
        // ACT
        String ret = bidListController.updateBid(id, bid0, result, model);
        //ASSERT
        assertThat(ret).hasToString("bidList/update");
    }


    @Test
    @DisplayName("Get /bidList/delete")
    void whenGetDelete_thenReturnBidList(){
        // ARRANGE
        Integer id = 1;
        BidList bid0 = new BidList("account0", "type0",  null);
        when(bidListService.findById(id)).thenReturn(bid0);
        // ACT
        String ret = bidListController.deleteBid(id, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/bidList/list");
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
