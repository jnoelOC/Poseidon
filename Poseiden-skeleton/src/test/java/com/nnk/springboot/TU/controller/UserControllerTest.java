package com.nnk.springboot.TU.controller;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.impl.UserService;
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
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    @DisplayName("Get users list")
    void whenGetUser_thenReturnsUserList() throws Exception {
        // ARRANGE
        User u1 = new User("unknown1", "User123!", "USER");
        User u2 = new User("unknown2", "User123!", "USER");
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        when(userService.findAll()).thenReturn(users);
        // ACT
        String ret = userController.home(model);
        // ASSERT
        assertThat(ret).hasToString("user/list");
    }

    @Test
    @DisplayName("Get user add")
    void whenGetUserAdd_thenReturnsString() throws Exception {
        // ARRANGE
        User u1 = new User("unknown1", "User123!", "USER");
        // ACT
        String ret = userController.addUser(u1);
        // ASSERT
        assertThat(ret).hasToString("user/add");
    }

    @Test
    @DisplayName("Post validate user")
    void whenPostValidateUser_thenReturnsUser() throws Exception {
        // ARRANGE
        User u1 = new User("user", "User123!", "USER");
        u1.setFullname("User");
        User u2 = new User("toutou", "User123!", "USER");
        u1.setFullname("Toutou");
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        when(userService.saveUser(u1)).thenReturn(u2);
        when(userService.findAll()).thenReturn(users);
        // ACT
        String ret = userController.validate(u1, result, model);
        // ASSERT
        assertThat(ret).hasToString("redirect:/user/list");
    }

    @Test
    @DisplayName("Post validate user with userName blank")
    void whenPostValidateUsernameAtBlank_thenReturnsString() throws Exception {
        // ARRANGE
        User u1 = new User("", "User123!", "USER");
        u1.setFullname("User");
        // ACT
        String ret = userController.validate(u1, result, model);
        // ASSERT
        assertThat(ret).hasToString("user/add");
    }

    @Test
    @DisplayName("Post validate user with fullName blank")
    void whenPostValidateFullnameAtBlank_thenReturnsString() throws Exception {
        // ARRANGE
        User u1 = new User("user", "User123!", "USER");
        u1.setFullname("");
        // ACT
        String ret = userController.validate(u1, result, model);
        // ASSERT
        assertThat(ret).hasToString("user/add");
    }

    @Test
    @DisplayName("Post validate user with password blank")
    void whenPostValidatePasswordAtBlank_thenReturnsString() throws Exception {
        // ARRANGE
        User u1 = new User("user", "", "USER");
        u1.setFullname("User");
        // ACT
        String ret = userController.validate(u1, result, model);
        // ASSERT
        assertThat(ret).hasToString("user/add");
    }
    @Test
    @DisplayName("Post validate user with Role blank")
    void whenPostValidateRoleAtBlank_thenReturnsString() throws Exception {
        // ARRANGE
        User u1 = new User("user", "User123!", "");
        u1.setFullname("User");
        // ACT
        String ret = userController.validate(u1, result, model);
        // ASSERT
        assertThat(ret).hasToString("user/add");
    }

    @Test
    @DisplayName("Get /user/Update with id")
    void whenGetUpdate_thenReturnUserUpdate(){
        // ARRANGE
        Long id = 1L;
        User u1 = new User("user", "User123!", "USER");
        u1.setFullname("User");
        when(userService.findById(id)).thenReturn(u1);
        // ACT
        String ret = userController.showUpdateForm(id, model);
        //ASSERT
        assertThat(ret).hasToString("user/update");
    }

    @Test
    @DisplayName("Post /user/Update with id")
    void whenPostUpdate_thenReturnUserUpdate(){
        // ARRANGE
        Long id = 1L;
        User u1 = new User("user", "User123!", "USER");
        u1.setFullname("User");
        User u2 = new User("toutou", "User123!", "USER");
        u1.setFullname("Toutou");
        List<User> users = new ArrayList<>();
        users.add(u1);         users.add(u2);
        when(userService.saveUser(u1)).thenReturn(u2);
        when(userService.findAll()).thenReturn(users);
        // ACT
        String ret = userController.updateUser(id, u1, result, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/user/list");
    }

    @Test
    @DisplayName("Post /user/Update with id")
    void whenPostUpdateWithUsernameAtBlank_thenReturnUserUpdate(){
        // ARRANGE
        Long id = 1L;
        User u1 = new User("", "User123!", "USER");
        u1.setFullname("User");
        // ACT
        String ret = userController.updateUser(id, u1, result, model);
        //ASSERT
        assertThat(ret).hasToString("user/update");
    }
    @Test
    @DisplayName("Post /user/Update with id")
    void whenPostUpdateWithFullnameAtBlank_thenReturnUserUpdate(){
        // ARRANGE
        Long id = 1L;
        User u1 = new User("user", "User123!", "USER");
        u1.setFullname("");
        // ACT
        String ret = userController.updateUser(id, u1, result, model);
        //ASSERT
        assertThat(ret).hasToString("user/update");
    }

    @Test
    @DisplayName("Post /user/Update with id")
    void whenPostUpdateWithPasswordAtBlank_thenReturnUserUpdate(){
        // ARRANGE
        Long id = 1L;
        User u1 = new User("user", "", "USER");
        u1.setFullname("User");
        // ACT
        String ret = userController.updateUser(id, u1, result, model);
        //ASSERT
        assertThat(ret).hasToString("user/update");
    }
    @Test
    @DisplayName("Post /user/Update with id")
    void whenPostUpdateWithRoleAtBlank_thenReturnUserUpdate(){
        // ARRANGE
        Long id = 1L;
        User u1 = new User("user", "User123!", "");
        u1.setFullname("User");
        // ACT
        String ret = userController.updateUser(id, u1, result, model);
        //ASSERT
        assertThat(ret).hasToString("user/update");
    }

    @Test
    @DisplayName("Post /user/Update with id")
    void whenPostUpdateWithSaveAtNull_thenReturnUserUpdate(){
        // ARRANGE
        Long id = 1L;
        User u1 = new User("user", "User123!", "USER");
        u1.setFullname("User");
        when(userService.saveUser(u1)).thenReturn(null);
        // ACT
        String ret = userController.updateUser(id, u1, result, model);
        //ASSERT
        assertThat(ret).hasToString("user/update");
    }

    @Test
    @DisplayName("Get /user/delete")
    void whenGetDelete_thenReturnUser(){
        // ARRANGE
        Long id = 1L;
        User u1 = new User("user", "User123!", "USER");
        u1.setFullname("User");
        when(userService.findById(id)).thenReturn(u1);
        // ACT
        String ret = userController.deleteUser(id, model);
        //ASSERT
        assertThat(ret).hasToString("redirect:/user/list");
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
