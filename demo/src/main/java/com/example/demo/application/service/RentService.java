package com.example.demo.application.service;

import com.example.demo.api.payload.request.RentDto;
import com.example.demo.api.payload.response.RentResponse;
import com.example.demo.application.error.exception.CustomException;

import java.util.List;

public interface RentService {
    RentResponse createRent(RentDto createRentDto) throws CustomException;
    RentResponse deleteRent(long userId, long bookId) throws CustomException;
    List<RentResponse> getRentedBooks();
}
