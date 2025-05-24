package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "Error Response",
description = "Schema To Hold Error Response Information")
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(description = "API path Invoke by client")
    private String apiPath;

    @Schema(description = "Error Code representing Error Happened")
    private HttpStatus  errorCode;

    @Schema(description = "Error Message representing  Error happened")
    private String errorMsg;
    private LocalDateTime errorTime;

}
