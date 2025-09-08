package com.example.demo.api.payload.request;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookRequest {
    private String name;
    private String author;
    private long bookCount;
    private long rentCount;
}
