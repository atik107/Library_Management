package com.example.demo.api.exceptionresolver;

import com.example.demo.application.error.dto.ErrorResponse;
import com.example.demo.application.error.exception.CustomException;
import com.example.demo.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice


public class GlobalExceptionResolver {

    public static String COMPONENT_CODE = Constants.Symbols.COMPONENT_CODE;

    public String getFeatureCode() {
        return "000_";
    }

    public String getFeatureMessage() {
        return "Unknown Feature. ";
    }

    @ExceptionHandler({CustomException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    ErrorResponse handleException(CustomException exception) {

        return ErrorResponse.builder()
                .reason(constructReasonCode(exception))
                .message(getFeatureMessage() + exception.getMessage())
                .build();
    }

    private String constructReasonCode(CustomException exception) {
        return COMPONENT_CODE + getFeatureCode() + exception.getCode();
    }
}
