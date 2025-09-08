package com.example.demo.application.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book_table")

public class BookEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_id"
    )
    @SequenceGenerator(
            name = "book_id",
            sequenceName = "book_id",
            allocationSize = 1,
            initialValue = 1
    )
    @Column(name = "book_id" , updatable = false, nullable = false , unique = true)
    private long id;

    @Column(name = "book_name" , length = 100)
    private String name;

    @Column(name = "boook_author", length = 100, nullable = true)
    private String author;

    @Column(name = "boook_count")
    private long bookCount;

    @Column(name = "rent_count")
    private long rentCount;

}



