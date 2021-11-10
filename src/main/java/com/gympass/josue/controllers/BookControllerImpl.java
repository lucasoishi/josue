package com.gympass.josue.controllers;

import com.gympass.josue.controllers.contracts.BookController;
import com.gympass.josue.controllers.representations.AuthorBooks;
import com.gympass.josue.controllers.representations.BookCreationRequest;
import com.gympass.josue.controllers.representations.NameUpdateRequest;
import com.gympass.josue.models.Book;
import com.gympass.josue.services.BookService;
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
        return bookService.listABook(id);
    }

    @PostMapping
    public Book postBook(@RequestBody @Valid BookCreationRequest book) {
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
                                                  @Valid @RequestBody Book book) {
        return bookService.createOrUpdateBook(id, book);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBookName(@PathVariable UUID id,
                                            @RequestBody NameUpdateRequest update) {
        return bookService.updateBookName(id, update);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteById(id);
    }
}
