package com.gympass.josue.controllers;

import static org.assertj.core.api.Assertions.assertThat;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerImplTest {
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
    public void shouldReturnDefaultMessage() throws Exception {
        var book = repository.save(new Book("name", "author", Publisher.PUBLISHER_A, 125));
        var jsonBook = objectMapper.writeValueAsString(book);
        var url =
        mockMvc.perform(
                get("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonBook));
    }

    @Test
    public void shouldReturnOKWhenListingBooks() throws Exception {
        var url =
                mockMvc.perform(
                                get("/books/")
                                        .contentType(MediaType.APPLICATION_JSON)
                        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnOKWhenCreatingABook() throws Exception {
        var book = new Book("name123", "author", Publisher.PUBLISHER_A, 125);
        var jsonBook = objectMapper.writeValueAsString(book);
        var url =
                mockMvc.perform(
                        post("/books/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonBook)
                ).andExpect(MockMvcResultMatchers.status().isOk());
        var bookSaved = repository.findByName(book.getName());
        assertThat(bookSaved).isNotNull();
        assertThat(bookSaved).hasSize(1);
        assertThat(bookSaved.get(0).getName()).isEqualTo(book.getName());
    }
    @Test
    public void shouldReturnOkWhenListingBooksPerAuthors() throws Exception {

    }
}
