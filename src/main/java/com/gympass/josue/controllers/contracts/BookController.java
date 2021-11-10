package com.gympass.josue.controllers.contracts;

import com.gympass.josue.controllers.representations.AuthorBooks;
import com.gympass.josue.controllers.representations.BookCreationRequest;
import com.gympass.josue.controllers.representations.NameUpdateRequest;
import com.gympass.josue.models.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
public interface BookController {

    Iterable<Book> getBooks();

    ResponseEntity<Optional<Book>> getBookById(@PathVariable UUID id);

    Book postBook(@RequestBody @Valid BookCreationRequest book);

    ResponseEntity<Optional<Book>> putBook(@PathVariable UUID id,
                                           @Valid @RequestBody Book book);

    void deleteBook(@PathVariable UUID id);

    ResponseEntity<?> updateBookName(@PathVariable UUID id,
                                     @RequestBody NameUpdateRequest update);

    List<AuthorBooks> booksPerAuthor(@PathVariable String author);

    List<AuthorBooks> booksPerAuthors();

}
