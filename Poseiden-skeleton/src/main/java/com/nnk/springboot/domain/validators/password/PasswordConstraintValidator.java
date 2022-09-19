package com.nnk.springboot.domain.validators.password;

import lombok.SneakyThrows;
import org.passay.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword arg0){
    }

    @SneakyThrows
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context){
        // customizing validation msg
        Properties props = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("passay.properties");
        try {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MessageResolver resolver = new PropertiesMessageResolver(props);

        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(
                // Password length should be in between 8 to 24 characters
                new LengthRule(4, 224),
                // with at least one uppercase
                // new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // with at least one symbol
                // new CharacterRule(EnglishCharacterData.Special, 1),
                // No whitespace allowed
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()){
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(",", messages);
       context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation().disableDefaultConstraintViolation();
        return false;
    }

}
