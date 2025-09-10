package com.example.demo.api.exceptionresolver;

import com.example.demo.api.endpoint.RentController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = RentController.class)

public class RentExceptionResolver extends GlobalExceptionResolver {

    @Override
    public String getFeatureCode() {
        return "002_";
    }

    @Override
    public String getFeatureMessage() {
        return "Rent Feature. ";
    }
}
