package ru.mezenova.MySecondTestAppSpringBoot.model;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class Response {
    @NotBlank
    private String uid;
    @NotBlank
    private String operationUid;
    @NotBlank
    private String operationTime;
    @NotBlank
    private String  systemTime;
    @NotBlank
    private String code;
    @NotBlank
    private String errorCode;
    @NotBlank
    private String errorMessage;

}
