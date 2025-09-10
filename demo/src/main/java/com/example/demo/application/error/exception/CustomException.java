package com.example.demo.application.error.exception;


import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CustomException extends Exception {

    String code;
    String message;
}
