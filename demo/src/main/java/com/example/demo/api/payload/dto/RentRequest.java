package com.example.demo.api.payload.dto;

import com.example.demo.application.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RentRequest {
    private String bookName;
    private StatusType status;
}
