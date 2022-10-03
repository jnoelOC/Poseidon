package com.nnk.springboot.TU.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.impl.RatingService;
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
public class RatingControllerTest {

    @InjectMocks
    private RatingController ratingController;

    @Mock
    private RatingService ratingService;


    @Test
    @DisplayName("Get ratings list")
    void whenGetRating_thenReturnsRatingsList() throws Exception {
        // ARRANGE
        Rating r1 = new Rating("moody1", "sand1", "fitch1", 111);
        Rating r2 = new Rating("moody2", "sand2", "fitch2", 222);
        List<Rating> ratings = new ArrayList<>();
        ratings.add(r1);
        ratings.add(r2);
        when(ratingService.findAllRatings()).thenReturn(ratings);
        // ACT
        String ret = ratingController.home(model);
        // ASSERT
        assertThat(ret).hasToString("rating/list");
    }

    @Test
    @DisplayName("Get /ratingList/add")
    void whenGetAddRating_thenReturnsRating() throws Exception {
        // ARRANGE
        // ACT
        String ret = ratingController.addRatingForm();
        // ASSERT
        assertThat(ret).hasToString("rating/add");
    }

    @Test
    @DisplayName("Post /rating/validate")
    void whenPostValidateRating_thenReturnsRatingsList() throws Exception {
        // ARRANGE
        Rating r1 = new Rating("moody1", "sand1", "fitch1", 111);
        Rating r2 = new Rating("moody2", "sand2", "fitch2", 222);
        List<Rating> ratings = new ArrayList<>();
        ratings.add(r1);
        ratings.add(r2);
        when(ratingService.saveRating(r1)).thenReturn(r2);
        when(ratingService.findAllRatings()).thenReturn(ratings);
        // ACT
        String ret = ratingController.validate(r1, result, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/rating/list");
    }

    @Test
    @DisplayName("Post /rating/validate with Moodys blank")
    void whenPostValidateBidListWithMoodysBlank_thenReturnsRatingAdd() throws Exception {
        // ARRANGE
        Rating r1 = new Rating("", "sand1", "fitch1", 111);
        // ACT
        String ret = ratingController.validate(r1, result, model);
        //ASSERT
        assertThat(ret).hasToString("rating/add");
    }

    @Test
    @DisplayName("Post /rating/validate with OrderNumber at null")
    void whenPostValidateBidListWithOrderNumberNull_thenReturnsRatingAdd() throws Exception {
        // ARRANGE
        Rating r1 = new Rating("", "sand1", "fitch1", 0);
        // ACT
        String ret = ratingController.validate(r1, result, model);
        //ASSERT
        assertThat(ret).hasToString("rating/add");
    }

    @Test
    @DisplayName("Post /rating/validate with OrderNumber at max")
    void whenPostValidateBidListWithOrderNumberAtMax_thenReturnsRatingAdd() throws Exception {
        // ARRANGE
        Rating r1 = new Rating("moody", "sand1", "fitch1", Integer.MAX_VALUE);
        // ACT
        String ret = ratingController.validate(r1, result, model);
        //ASSERT
        assertThat(ret).hasToString("rating/add");
    }

    @Test
    @DisplayName("Get /rating/Update with id")
    void whenGetUpdate_thenReturnRatingUpdate(){
        // ARRANGE
        Integer id = 1;
        Rating r1 = new Rating("moody", "sand1", "fitch1", 100);
        when(ratingService.findById(id)).thenReturn(r1);
        // ACT
        String ret = ratingController.showUpdateForm(id, model);
        //ASSERT
        assertThat(ret).hasToString("rating/update");
    }

    @Test
    @DisplayName("Post /rating/Update with id")
    void whenPostUpdate_thenReturnRatingUpdate(){
        // ARRANGE
        Integer id = 1;
        Rating r1 = new Rating("moody1", "sand1", "fitch1", 111);
        Rating r2 = new Rating("moody2", "sand2", "fitch2", 222);
        List<Rating> ratings = new ArrayList<>();
        ratings.add(r1);
        ratings.add(r2);
        when(ratingService.saveRating(r1)).thenReturn(r2);
        // ACT
        String ret = ratingController.updateRating(id, r1, result, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/rating/list");
    }

    @Test
    @DisplayName("Post /rating/Update with Save returns null")
    void whenPostUpdate_thenReturnRatingAtNull(){
        // ARRANGE
        Integer id = 1;
        Rating r1 = new Rating("moody1", "sand1", "fitch1", 111);
        when(ratingService.saveRating(r1)).thenReturn(null);
        // ACT
        String ret = ratingController.updateRating(id, r1, result, model);
        //ASSERT
        assertThat(ret).hasToString("rating/update");
    }

    @Test
    @DisplayName("Post /rating/update with OrderNumber at -1")
    void whenPostUpdateRatingWithOrderNumberNull_thenReturnsRatingAdd() throws Exception {
        // ARRANGE
        Rating r1 = new Rating("moody", "sand1", "fitch1", -1);
        // ACT
        String ret = ratingController.validate(r1, result, model);
        //ASSERT
        assertThat(ret).hasToString("rating/add");
    }

    @Test
    @DisplayName("Post /rating/update with OrderNumber at max")
    void whenPostUpdateRatingWithOrderNumberAtMax_thenReturnsRatingAdd() throws Exception {
        // ARRANGE
        Rating r1 = new Rating("moody", "sand1", "fitch1", Integer.MAX_VALUE);
        // ACT
        String ret = ratingController.validate(r1, result, model);
        //ASSERT
        assertThat(ret).hasToString("rating/add");
    }

    @Test
    @DisplayName("Get /rating/delete")
    void whenGetDelete_thenReturnRating(){
        // ARRANGE
        Integer id = 1;
        Rating r1 = new Rating("moody1", "sand1", "fitch1", 123);
        when(ratingService.findById(id)).thenReturn(r1);
        // ACT
        String ret = ratingController.deleteRating(id, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/rating/list");
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
