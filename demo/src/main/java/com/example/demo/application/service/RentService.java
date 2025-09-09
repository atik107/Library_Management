package com.example.demo.application.service;

import com.example.demo.api.payload.request.RentRequest;
import com.example.demo.api.payload.response.RentResponse;

import java.util.List;

public interface RentService {
    RentResponse createRent(RentRequest createRentRequest);
    RentResponse deleteRent(long userId, long bookId);
    List<RentResponse> getRentedBooks();
}
