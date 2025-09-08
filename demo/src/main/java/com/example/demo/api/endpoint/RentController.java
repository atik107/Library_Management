package com.example.demo.api.endpoint;

import com.example.demo.api.payload.request.RentRequest;
import com.example.demo.api.payload.response.RentResponse;
import com.example.demo.application.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rent")

public class RentController {
    private final RentService rentService;


    @PostMapping
    public RentResponse CreateRent(@RequestBody RentRequest createRentRequest) {
        return rentService.createRent(createRentRequest);
    }

    @DeleteMapping("/{userId}/{bookId}")
    public RentResponse DeleteRent(@PathVariable long userId, @PathVariable long bookId) {
        return rentService.deleteRent(userId, bookId);
    }
}
