package com.example.demo.api.payload.response;

import com.example.demo.api.payload.request.RentRequest;
import com.example.demo.application.domain.BookEntity;
import com.example.demo.application.domain.RentEntity;
import com.example.demo.application.enums.StatusType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RentResponse {
//    private long rentId;
    private String bookName;
    private String author;
    private long bookCount;
    private long rentCount;
    private StatusType status;
    private long userId;
    private String userName;
    private long bookId;


    public static RentResponse fromRent(RentEntity rentEntity) {
        return RentResponse.builder()
//                .rentId(rentEntity.getId())
                .userId(rentEntity.getUserId())
                .bookId(rentEntity.getBookId())
                .build();
    }
}
