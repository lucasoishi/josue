package com.gympass.josue.repositories;

import com.gympass.josue.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BookRepository extends CrudRepository<Book, String> {
    public List<Book> findAll();
    public Optional<Book> findById(UUID id);
    public boolean existsById(UUID id);
    public void deleteById(UUID id);
    public List<Book> findByAuthor(String author);
    public List<Book> findByName(String name);
}
