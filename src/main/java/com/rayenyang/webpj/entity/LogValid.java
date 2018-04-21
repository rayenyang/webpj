package com.rayenyang.webpj.entity;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * description:
 * Created by rayenyang on 2017/3/1.
 */
public class LogValid implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        final boolean equals = Log.class.equals(aClass);
        return equals;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "start", "start不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "count", "count不能为空");
        if (o != null) {
            Log log = (Log) o;
            if(log.getCount() == null){
                errors.rejectValue("count", "count不能为空");
            }else {
                if (log.getCount() < 0) {
                    errors.rejectValue("count", "count不能为负数");
                }
            }
        }
    }
}
