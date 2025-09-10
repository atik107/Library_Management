package com.example.demo.api.payload.request;

import com.example.demo.application.enums.StatusType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentDto {
    private String bookname;
    private StatusType status;
    private long userId;
}
