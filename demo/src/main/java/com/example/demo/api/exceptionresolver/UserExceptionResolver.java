package com.example.demo.api.exceptionresolver;

import com.example.demo.api.endpoint.UserController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionResolver extends GlobalExceptionResolver {

    @Override
    public String getFeatureCode() {
        return "003_";
    }

    @Override
    public String getFeatureMessage() {
        return "User Feature. ";
    }
}
