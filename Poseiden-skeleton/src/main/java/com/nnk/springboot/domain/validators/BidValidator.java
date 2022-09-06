package com.nnk.springboot.domain.validators;


import com.nnk.springboot.domain.BidList;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("beforeCreateBidValidator")
public class BidValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return BidList.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "account", "account.empty");
        BidList bid = (BidList) obj;
        if (checkInputString(bid.getType())) {
            errors.rejectValue("type", "type.empty");
        }
        if (checkInputString(bid.getAccount())) {
            errors.rejectValue("account", "account.empty");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
