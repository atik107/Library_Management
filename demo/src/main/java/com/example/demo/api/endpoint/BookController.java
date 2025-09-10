package com.example.demo.api.endpoint;

import com.example.demo.api.payload.request.BookRequest;
import com.example.demo.api.payload.response.BookResponse;
import com.example.demo.application.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
@Tag(name = "Book API")



public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookResponse createBook(@RequestBody BookRequest newbook) {
        return bookService.createBook(newbook);
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable("id") long id) {
        return bookService.getBook(id);
    }

    @GetMapping
    public List<BookResponse> getAllBook() {
        return bookService.getAllBook();
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable("id") long id, @RequestBody BookRequest updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public BookResponse deleteBook(@PathVariable("id") long id) {
        return bookService.deleteBook(id);
    }
}
