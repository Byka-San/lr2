package ru.mezenova.MySecondTestAppSpringBoot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Codes code;
    @NotBlank
    private ErrorCodes errorCode;
    @NotBlank
    private ErrorMessages errorMessage;
}
