package com.gympass.josue.controllers;

import com.gympass.josue.controllers.contracts.BookController;
import com.gympass.josue.controllers.representations.BookRequest;
import com.gympass.josue.controllers.representations.BookResponse;
import com.gympass.josue.controllers.representations.NameUpdateRequest;
import com.gympass.josue.models.Book;
import com.gympass.josue.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookControllerImpl implements BookController {
    private final BookService bookService;

    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Iterable<BookResponse>> getBooks() {
        return new ResponseEntity<>(bookService.listBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BookResponse>> getBookById(@PathVariable UUID id) {
        var book = bookService.listABook(id);
        var httpStatus = book.map(
                b -> HttpStatus.OK
        ).orElse(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(book, httpStatus);
    }

    @PostMapping
    public ResponseEntity<BookResponse> postBook(@RequestBody @Valid BookRequest book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<BookResponse>> putBook(@PathVariable UUID id,
                                                  @Valid @RequestBody BookRequest bookRequest) {
        return bookService.updateBookAttributes(id, bookRequest).map(
                b -> new ResponseEntity<>(Optional.of(b), HttpStatus.OK)).orElse(new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Optional<BookResponse>> updateBookName(@PathVariable UUID id,
                                                         @RequestBody NameUpdateRequest update) {
        return bookService.updateBookName(id, update).map(b -> new ResponseEntity<>(Optional.of(b), HttpStatus.OK)).orElse(new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteById(id);
    }
}
