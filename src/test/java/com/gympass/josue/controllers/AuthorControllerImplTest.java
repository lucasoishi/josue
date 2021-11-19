package com.gympass.josue.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gympass.josue.models.Book;
import com.gympass.josue.models.Enums.Language;
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

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookRepository repository;

    @AfterAll
    public void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void shouldReturnOkWhenListingBooksPerAuthors() throws Exception {
        for (int i = 0; i < 5; i++) {
            repository.save(new Book("name", "author", Publisher.PUBLISHER_A, 125, null, null, false, null, Language.UNKNOWN));
        }
        mockMvc.perform(
                get("/authors/books")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnOkWhenListingBooksPerAuthor() throws Exception {
        var authorName = UUID.randomUUID();
        for (int i = 0; i < 5; i++) {
            repository.save(new Book("name", "author x", Publisher.PUBLISHER_A, 125, null, null, false, null, Language.UNKNOWN));
        }
        mockMvc.perform(
                        get("/authors/" + authorName + "/books")
                ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
