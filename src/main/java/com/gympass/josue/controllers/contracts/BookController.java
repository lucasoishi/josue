package com.gympass.josue.controllers.contracts;

import com.gympass.josue.controllers.representations.BookRequest;
import com.gympass.josue.controllers.representations.BookResponse;
import com.gympass.josue.controllers.representations.NameUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Validated
public interface BookController {

    ResponseEntity<Iterable<BookResponse>> getBooks();

    ResponseEntity<Optional<BookResponse>> getBookById(@PathVariable UUID id);

    ResponseEntity<BookResponse> postBook(@RequestBody @Valid BookRequest book);

    ResponseEntity<Optional<BookResponse>> putBook(@PathVariable UUID id,
                                                   @Valid @RequestBody BookRequest bookRequest);

    void deleteBook(@PathVariable UUID id);

    ResponseEntity<Optional<BookResponse>> updateBookName(@PathVariable UUID id,
                                                          @Valid @RequestBody NameUpdateRequest update);

}
