package com.gympass.josue.repositories;

import com.gympass.josue.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;


public interface BookRepository extends CrudRepository<Book, String> {
    @Query("SELECT b FROM Book b")
    public Stream<Book> streamAll();
    public List<Book> findAll();
    public Optional<Book> findById(UUID id);
    public void deleteById(UUID id);
    public Stream<Book> streamByAuthor(String author);
    public List<Book> findByName(String name);
}
