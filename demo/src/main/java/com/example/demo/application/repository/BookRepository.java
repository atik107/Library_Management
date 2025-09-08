package com.example.demo.application.repository;

import com.example.demo.application.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findByName(String bookName);
    //BookEntity findByBookName(String bookName);

}
