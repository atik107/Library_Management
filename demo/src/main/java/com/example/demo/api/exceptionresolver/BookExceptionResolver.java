package com.example.demo.api.exceptionresolver;

import com.example.demo.api.endpoint.BookController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = BookController.class)

public class BookExceptionResolver extends GlobalExceptionResolver {

    @Override
    public String getFeatureCode() {
        return "001_";
    }

    @Override
    public String getFeatureMessage() {
        return "Book Feature. ";
    }
}
