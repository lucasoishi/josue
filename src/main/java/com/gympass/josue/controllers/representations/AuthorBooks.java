package com.gympass.josue.controllers.representations;

import com.gympass.josue.models.Book;

import java.util.List;

public class AuthorBooks {
    private List<BookResponse> books;
    private String author;

    public AuthorBooks(String author, List<BookResponse> books) {
        this.author = author;
        this.books = books;
    }

    public List<BookResponse> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponse> books) {
        this.books = books;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
