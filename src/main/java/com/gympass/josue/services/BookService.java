package com.gympass.josue.services;

import com.gympass.josue.controllers.representations.AuthorBooks;
import com.gympass.josue.controllers.representations.BookRequest;
import com.gympass.josue.controllers.representations.NameUpdateRequest;
import com.gympass.josue.models.Book;
import com.gympass.josue.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> listBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> listABook(UUID id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book createBook(BookRequest bookRequest) {
        Book book = Book.fromBookCreationRequest(bookRequest);
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteById(UUID id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public Optional<Book> updateBookAttributes(UUID id, BookRequest bookRequest) {
        var book =  bookRepository.findById(id);
        if (book.isPresent()) {
            var bookToSave = Book.fromBookCreationRequest(bookRequest);
            bookToSave.setId(id);
         return Optional.of(bookRepository.save(bookToSave));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<Book> updateBookName(UUID id, NameUpdateRequest update) {
        return bookRepository.findById(id).map(b -> updateBookName(b, update.getName())).or(Optional::empty);
    }

    public List<AuthorBooks> listBooksPerAuthors() {
        var collection = bookRepository.findAll()
                .stream()
                .collect(groupingBy(Book::getAuthor))
                .entrySet()
                .stream()
                .map(e -> new AuthorBooks(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        return collection;
    }

    public List<AuthorBooks> listBooksPerAuthor(String author) {
        var collection = bookRepository.findByAuthor(author)
                .stream()
                .collect(groupingBy(Book::getAuthor))
                .entrySet()
                .stream()
                .map(e -> new AuthorBooks(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        return collection;
    }
    private Book updateBookName(Book book, String name) {
        book.setName(name);
        return book;
    }
}
