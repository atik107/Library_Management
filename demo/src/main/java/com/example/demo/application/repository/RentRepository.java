package com.example.demo.application.repository;

import com.example.demo.application.domain.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentRepository extends JpaRepository<RentEntity, Long> {

    List<RentEntity> findByUserIdAndBookIdOrderByRentDateAsc(long userId, long bookId);

    long countByUserId(long userId);

    List<RentEntity> findByBookIdOrderByRentDateDesc(long bookId);

    // find distinct book id
    @Query("SELECT DISTINCT r.bookId FROM RentEntity r")
    List<Long> findDistinctBookIds();


}




