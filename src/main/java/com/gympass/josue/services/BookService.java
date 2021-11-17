package com.gympass.josue.services;

import com.gympass.josue.controllers.representations.AuthorBooks;
import com.gympass.josue.controllers.representations.BookRequest;
import com.gympass.josue.controllers.representations.BookResponse;
import com.gympass.josue.controllers.representations.NameUpdateRequest;
import com.gympass.josue.models.Book;
import com.gympass.josue.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<BookResponse> listBooks() {
        return bookRepository.findAll().stream().map(BookResponse::fromBook).collect(Collectors.toList());
    }

    public Optional<BookResponse> listABook(UUID id) {
        return bookRepository.findById(id).map(BookResponse::fromBook);
    }

    @Transactional
    public BookResponse createBook(BookRequest bookRequest) {
        Book book = Book.fromBookCreationRequest(bookRequest);
        return BookResponse.fromBook(bookRepository.save(book));
    }

    @Transactional
    public void deleteById(UUID id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public Optional<BookResponse> updateBookAttributes(UUID id, BookRequest bookRequest) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            var bookToSave = Book.fromBookCreationRequest(bookRequest);
            bookToSave.setId(id);
            return Optional.of(BookResponse.fromBook(bookRepository.save(bookToSave)));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<BookResponse> updateBookName(UUID id, NameUpdateRequest update) {
        return bookRepository.findById(id).map(b -> updateBookName(b, update.getName())).map(BookResponse::fromBook).or(Optional::empty);
    }

    @Transactional
    public List<AuthorBooks> listBooksPerAuthors() {
        return bookRepository.streamAll().map(BookResponse::fromBook)
                .collect(groupingBy(BookResponse::getAuthor))
                .entrySet()
                .stream()
                .map(e -> new AuthorBooks(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<List<AuthorBooks>> listBooksPerAuthor(String author) {
        return Optional.of(bookRepository.streamByAuthor(author).map(BookResponse::fromBook)
                .collect(groupingBy(BookResponse::getAuthor))
                .entrySet()
                .stream()
                .map(e -> new AuthorBooks(e.getKey(), e.getValue()))
                .collect(Collectors.toList()));
    }

    private Book updateBookName(Book book, String name) {
        book.setName(name);
        return book;
    }
}
