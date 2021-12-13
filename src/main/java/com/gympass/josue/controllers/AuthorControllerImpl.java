package com.gympass.josue.controllers;

import com.gympass.josue.controllers.contracts.AuthorController;
import com.gympass.josue.controllers.representations.AuthorBooks;
import com.gympass.josue.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorControllerImpl implements AuthorController {
    private final BookService bookService;

    public AuthorControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<AuthorBooks> booksPerAuthors() {
        return bookService.listBooksPerAuthors();
    }

    @GetMapping("/{author}/books")
    public Optional<List<AuthorBooks>> booksPerAuthor(@PathVariable String author) {
        return bookService.listBooksPerAuthor(author);
    }
}
