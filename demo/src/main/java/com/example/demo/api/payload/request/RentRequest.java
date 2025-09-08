package com.example.demo.api.payload.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RentRequest {
    private String bookname;
    private String status;
    private long userId;
}
