package ru.mezenova.MySecondTestAppSpringBoot.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Request {
    @NotBlank
    @Size(max = 32)

    private String uid;

    @NotBlank
    @Size(max = 32)

    private String operationUid;
    private String systemName;

    @NotBlank

    private String  systemTime;
    private String source;

    @Min(1)
    @Max(100000)

    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;
}
