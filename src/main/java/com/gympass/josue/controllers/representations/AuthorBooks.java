package com.gympass.josue.controllers.representations;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors
public @Data
class AuthorBooks {
    private List<BookResponse> books;
    private String author;

    public AuthorBooks(String author, List<BookResponse> books) {
        this.books = books;
        this.author = author;
    }
}
