package ru.mezenova.MySecondTestAppSpringBoot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import ru.mezenova.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public class RequestValidationService implements ValidationService {
    private static final Logger logger = LoggerFactory.getLogger(RequestValidationService.class);

    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if(bindingResult.hasErrors()){
            logger.error("Validation failed: {}", bindingResult.getFieldError());
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}
