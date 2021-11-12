package com.gympass.josue.controllers;

import com.gympass.josue.controllers.contracts.BookController;
import com.gympass.josue.controllers.representations.AuthorBooks;
import com.gympass.josue.controllers.representations.BookRequest;
import com.gympass.josue.controllers.representations.NameUpdateRequest;
import com.gympass.josue.models.Book;
import com.gympass.josue.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookControllerImpl implements BookController {
    private BookService bookService;

    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getBooks() {
        return bookService.listBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable UUID id) {
        var book =  bookService.listABook(id);
        var httpStatus =book.map(
                b -> HttpStatus.OK
        ).orElse(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(book, httpStatus);
    }

    @PostMapping
    public Book postBook(@RequestBody @Valid BookRequest book) {
        return bookService.createBook(book);
    }

    @GetMapping("/authors")
    public List<AuthorBooks> booksPerAuthors() {
        return bookService.listBooksPerAuthors();
    }

    @GetMapping("/authors/{author}")
    public List<AuthorBooks> booksPerAuthor(@PathVariable String author) {
        return bookService.listBooksPerAuthor(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Book>> putBook(@PathVariable UUID id,
                                  @Valid @RequestBody BookRequest bookRequest) {
        var book =  bookService.updateBookAttributes(id, bookRequest);
        var httpStatus =book.map(
                b -> HttpStatus.OK
        ).orElse(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(book, httpStatus);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBookName(@PathVariable UUID id,
                                            @RequestBody NameUpdateRequest update) {
        bookService.updateBookName(id, update);
        return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteById(id);
    }
}
