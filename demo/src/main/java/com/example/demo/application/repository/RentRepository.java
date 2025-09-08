package com.example.demo.application.repository;

import com.example.demo.application.domain.RentEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentRepository extends JpaRepository<RentEntity,Long> {

//    @Query("SELECT r FROM RentEntity r WHERE r.userId = :userId AND r.bookId = :bookId ORDER BY r.rentDate ASC")
//    List<RentEntity> findByUserIdAndBookId(@Param("userId") long userId, @Param("bookId") long bookId);

    List<RentEntity> findByUserIdAndBookIdOrderByRentDateAsc(long userId, long bookId);


}




