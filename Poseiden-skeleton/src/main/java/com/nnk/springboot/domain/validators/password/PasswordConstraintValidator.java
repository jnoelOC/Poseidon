package com.nnk.springboot.domain.validators.password;

import org.passay.*;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

@Component
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword arg0){
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context){

        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // Password length should be in between 8 to 125 characters
                new LengthRule(8,125),
                // with at least one uppercase
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // with at least one digit
                new CharacterRule(EnglishCharacterData.Digit, 1),
                // with at least one symbol
                new CharacterRule(EnglishCharacterData.Special, 1),
                // No whitespace allowed
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()){
            return true;
        }

        context.buildConstraintViolationWithTemplate("Le mot de passe devrait contenir au moins 8 caractères, 1 majuscule, 1 chiffre et 1 caractère spécial")
                .addConstraintViolation().disableDefaultConstraintViolation();
        return false;
    }

}
