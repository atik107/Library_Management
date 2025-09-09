package com.example.demo.api.endpoint;

import com.example.demo.api.payload.dto.RentDto;
import com.example.demo.api.payload.request.RentRequest;
import com.example.demo.api.payload.response.RentResponse;
import com.example.demo.application.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rent")

public class RentController {
    private final RentService rentService;


    @PostMapping
    public RentResponse CreateRent(@RequestHeader("X-KM-UserId") long userId,
                                   @RequestHeader("X-KM-BookId") long bookId,
                                   @RequestBody RentDto rentDto) {

        RentRequest rentRequest = new RentRequest();

        rentRequest.setUserId(userId);
        rentRequest.setStatus(rentDto.getStatus());
        rentRequest.setBookname(rentDto.getBookName());


        switch (rentRequest.getStatus()) {
            case RENT:
                return rentService.createRent(rentRequest);
            case RETURN:
                return rentService.deleteRent(userId, bookId);
            default:
                throw new IllegalStateException("Unexpected value: " + rentRequest.getStatus());
        }
    }

    @GetMapping("/books")
    public List<RentResponse> getRentedBooks() {
        return rentService.getRentedBooks();
    }



//    @DeleteMapping("/rent")
//    public RentResponse DeleteRent(@RequestHeader("X-KM-UserId") long userId, @RequestHeader("X-KM-UserId") long bookId) {
//        return rentService.deleteRent(userId, bookId);
//    }
}
