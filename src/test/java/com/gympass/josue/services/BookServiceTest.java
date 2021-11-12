package com.gympass.josue.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gympass.josue.models.Book;
import com.gympass.josue.models.Enums.Publisher;
import com.gympass.josue.repositories.BookRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookRepository repository;
    @Autowired
    private BookService service;

    @AfterAll
    public void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void shouldReturnOkWhenListingBooksPerAuthors() throws Exception {

    }
}
