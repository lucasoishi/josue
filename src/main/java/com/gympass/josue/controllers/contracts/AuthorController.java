package com.gympass.josue.controllers.contracts;

import com.gympass.josue.controllers.representations.AuthorBooks;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


public interface AuthorController {

    Optional<List<AuthorBooks>> booksPerAuthor(@PathVariable String author);

    List<AuthorBooks> booksPerAuthors();

}
