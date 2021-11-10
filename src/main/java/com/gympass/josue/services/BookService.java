package com.gympass.josue.services;

import com.gympass.josue.controllers.representations.AuthorBooks;
import com.gympass.josue.controllers.representations.BookCreationRequest;
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

    public ResponseEntity<Optional<Book>> listABook(UUID id) {
        Optional<Book> book = bookRepository.findById(id);
        return (book.isPresent())
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
    }

    @Transactional
    public Book createBook(BookCreationRequest bookRequest) {
        Book book = Book.fromBookCreationRequest(bookRequest);
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteById(UUID id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public ResponseEntity<Optional<Book>> createOrUpdateBook(UUID id, Book book) {
        book.setId(id);
        return (bookRepository.existsById(id))
                ? new ResponseEntity<>(Optional.of(bookRepository.save(book)), HttpStatus.OK)
                : new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<?> updateBookName(UUID id, NameUpdateRequest update) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            var bookToSave = book.get();
            bookToSave.setName(update.getName());
            bookRepository.save(bookToSave);
            return new ResponseEntity<>(bookToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
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
}
