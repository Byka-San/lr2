package ru.mezenova.MySecondTestAppSpringBoot.controller;

import java.util.Date;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import ru.mezenova.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.mezenova.MySecondTestAppSpringBoot.model.*;
import ru.mezenova.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.mezenova.MySecondTestAppSpringBoot.service.ValidationService;
import ru.mezenova.MySecondTestAppSpringBoot.util.DateTimeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    public MyController(ValidationService validationService, @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyResponseService){
        this.validationService=validationService;
        this.modifyResponseService=modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult ){

        logger.info("Received request: {}", request);

        Response response =  Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        logger.info("Generated response: {}", response);

        try {
            validationService.isValid(bindingResult);
        }
        catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);

            logger.error("Validation failed. Response: {}", response, e);
            return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){

            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);

            logger.error("Unknown error. Response: {}", response, e);
            return new ResponseEntity<Response>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        logger.info("Modified response: {}", response);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
