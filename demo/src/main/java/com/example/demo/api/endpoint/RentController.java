package com.example.demo.api.endpoint;

import com.example.demo.api.payload.dto.RentRequest;
import com.example.demo.api.payload.request.RentDto;
import com.example.demo.api.payload.response.RentResponse;
import com.example.demo.application.error.exception.CustomException;
import com.example.demo.application.service.RentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rent")
@Tag(name = "Book Rent API")

public class RentController {
    private final RentService rentService;


    @PostMapping
    public RentResponse CreateRent(@RequestHeader("X-KM-UserId") long userId,
                                   @RequestHeader("X-KM-BookId") long bookId,
                                   @RequestBody RentRequest rentRequest) throws CustomException {

        RentDto rentDto = RentDto.builder()
                .userId(userId)
                .bookname(rentRequest.getBookName())
                .status(rentRequest.getStatus())
                .build();

        switch (rentDto.getStatus()) {
            case RENT:
                return rentService.createRent(rentDto);
            case RETURN:
                return rentService.deleteRent(userId, bookId);
            default:
                throw new CustomException("5", "Unexpected value: " + rentDto.getStatus());
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
