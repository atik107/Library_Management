package com.example.demo.api.payload.response;

import com.example.demo.application.domain.BookEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private long id;
    private String name;
    private String author;

    public static BookResponse from(BookEntity entity) {
        return BookResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .author(entity.getAuthor())
                .build();
    }
}
