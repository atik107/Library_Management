package com.example.demo.application.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rent_table")

public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id" , unique = true, nullable = false)
    private long id;

    @Column(name = "user_id")
    private long userId;

    //@ManyToOne
    //@JoinColumn(name = "book_id")
    @Column(name = "book_id")
    private long bookId;

    @CreatedDate
    @Column(name = "rent_date")
    private LocalDateTime rentDate;
}
