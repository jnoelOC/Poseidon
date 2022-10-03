package com.nnk.springboot.TU.controller;

import com.nnk.springboot.controllers.CurvePointController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.impl.CurvePointService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurvePointControllerTest {

    @InjectMocks
    private CurvePointController curvePointController;

    @Mock
    private CurvePointService curvePointService;

    @Test
    @DisplayName("Get curvePoint/list")
    void whenGetCurvePoint_ThenReturnsCurvePointList(){
    // ARRANGE
        CurvePoint cp1 = new CurvePoint(1, 11.0, 22.0);
        CurvePoint cp2 = new CurvePoint(2, 33.0, 44.0);
        List<CurvePoint> curvePoints = new ArrayList<>();
        curvePoints.add(cp1);
        curvePoints.add(cp2);
        when(curvePointService.findAllCurvePoints()).thenReturn(curvePoints);

        // ACT
        String ret = curvePointController.home(model);
        // ASSERT
        assertThat(ret).hasToString("curvePoint/list");
    }

    @Test
    @DisplayName("Get curvePoint/add")
    void whenGetAddCurvePoint_ThenReturnsString(){
        // ARRANGE
        // ACT
        String ret = curvePointController.addCPForm();
        // ASSERT
        assertThat(ret).hasToString("curvePoint/add");
    }

    @Test
    @DisplayName("Post validate curvePoint")
    void whenPostCurvePointValidate_ThenReturnsCurvePointList(){
        // ARRANGE
        CurvePoint cp1 = new CurvePoint(1, 11.0, 22.0);
        cp1.setCurveId(4);
        CurvePoint cp2 = new CurvePoint(2, 33.0, 44.0);
        cp2.setCurveId(5);
        List<CurvePoint> curvePoints = new ArrayList<>();
        curvePoints.add(cp1);
        curvePoints.add(cp2);
        when(curvePointService.saveCurvePoint(cp1)).thenReturn(cp2);
        when(curvePointService.findAllCurvePoints()).thenReturn(curvePoints);
        // ACT
        String ret = curvePointController.validate(cp1, result, model);
        // ASSERT
        assertThat(ret).hasToString("redirect:/curvePoint/list");
    }

    @Test
    @DisplayName("Post validate with curvePointId at null")
    void whenPostCurvePointIdValidate_ThenReturnsCurvePointAddString(){
        // ARRANGE
        CurvePoint cp1 = new CurvePoint(1, 11.0, 22.0);
        // ACT
        String ret = curvePointController.validate(cp1, result, model);
        // ASSERT
        assertThat(ret).hasToString("curvePoint/add");
    }

    @Test
    @DisplayName("Get update with Id")
    void whenGetCurvePointUpdate_ThenReturnsCurvePointUpdateString(){
        // ARRANGE
        Integer id = 1;
        CurvePoint cp1 = new CurvePoint(2, 11.0, 22.0);
        when(curvePointService.findById(id)).thenReturn(cp1);
        // ACT
        String ret = curvePointController.showUpdateForm(id, model);
        // ASSERT
        assertThat(ret).hasToString("curvePoint/update");
    }

    @Test
    @DisplayName("Post update with Id")
    void whenPostCurvePointUpdate_ThenReturnsRedirectCurvePointUpdateString(){
        // ARRANGE
        Integer id = 1;
        CurvePoint cp1 = new CurvePoint(2, 11.0, 22.0);
        cp1.setCurveId(4);
        CurvePoint cp2 = new CurvePoint(3, 33.0, 44.0);
        cp2.setCurveId(5);
        when(curvePointService.saveCurvePoint(cp1)).thenReturn(cp2);

        // ACT
        String ret = curvePointController.updateCP(id, cp1, result, model);
        // ASSERT
        assertThat(ret).hasToString("redirect:/curvePoint/list");
    }

    @Test
    @DisplayName("Post update with CurvePointId at null")
    void whenPostCurvePointUpdate_ThenReturnsCurvePointUpdateString(){
        // ARRANGE
        Integer id = 1;
        CurvePoint cp1 = new CurvePoint(2, 11.0, 22.0);
        // ACT
        String ret = curvePointController.updateCP(id, cp1, result, model);
        // ASSERT
        assertThat(ret).hasToString("curvePoint/update");
    }

    @Test
    @DisplayName("Post update with Save at null")
    void whenPostCurvePointSave_ThenReturnsCurvePointUpdateString(){
        // ARRANGE
        Integer id = 1;
        CurvePoint cp1 = new CurvePoint(2, 11.0, 22.0);
        cp1.setCurveId(5);
        when(curvePointService.saveCurvePoint(cp1)).thenReturn(null);
        // ACT
        String ret = curvePointController.updateCP(id, cp1, result, model);
        // ASSERT
        assertThat(ret).hasToString("curvePoint/update");
    }

    @Test
    @DisplayName("Get curvepoint/delete")
    void whenGetDelete_thenReturnCurvePoint(){
        // ARRANGE
        Integer id = 1;
        CurvePoint cp1 = new CurvePoint(2, 11.0, 22.0);
        cp1.setCurveId(5);
        when(curvePointService.findById(id)).thenReturn(cp1);
        // ACT
        String ret = curvePointController.deleteCP(id, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/curvePoint/list");
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
