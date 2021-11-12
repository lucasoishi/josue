package com.gympass.josue.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gympass.josue.controllers.representations.BookRequest;
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
    public void shouldReturnOKWhenListingAnExistingBook() throws Exception {
        var book = repository.save(new Book("name", "author", Publisher.PUBLISHER_A, 125, null, Language.UNKNOWN, null, null, false));
        var jsonBook = objectMapper.writeValueAsString(book);
        mockMvc.perform(
                get("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonBook));
    }

    @Test
    public void shouldReturnNotFoundWhenIdDoesNotExistInDB() throws Exception {
        mockMvc.perform(
                get("/books/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldReturnOKWhenListingBooks() throws Exception {
        mockMvc.perform(
                get("/books/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnOKWhenCreatingABook() throws Exception {
        var book = new Book("name123", "author", Publisher.PUBLISHER_A, 125, null, Language.UNKNOWN, null, null, false);
        var jsonBook = objectMapper.writeValueAsString(book);
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
    public void shouldReturnBadRequestWhenTryingToCreateABookWithMissingRequiredField() throws Exception {
        var json_payload = "{\"author\": \"Josue\", \"publisher\": \"PUBLISHER_A\", \"pages\": 153}";
        mockMvc.perform(
                post("/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_payload)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldReturnOkAndCorrectlyUpdateName() throws Exception {
        var book = repository.save(new Book("name", "author", Publisher.PUBLISHER_A, 125, null, Language.UNKNOWN, null, null, false));
        var new_name = "name 2";
        var payload = "{\"name\": \"" + new_name + "\"}";
        mockMvc.perform(
                patch("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(payload));
        assertThat(repository.findById(book.getId()).get().getName()).isEqualTo(new_name);
    }

    @Test
    public void shouldReturnNotFoundIfIdDoesNotExistWhenTryingToUpdateName() throws Exception {
        var new_name = "name 2";
        var payload = "{\"name\": \"" + new_name + "\"}";
        mockMvc.perform(
                patch("/books/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldReturnOkWhenListingBooksPerAuthors() throws Exception {
        for (int i = 0; i < 5; i++) {
            repository.save(new Book("name", "author", Publisher.PUBLISHER_A, 125, null, Language.UNKNOWN, null, null, false));
        }
        mockMvc.perform(
                get("/books/authors/author")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnOkWhenUpdatingABooksAttributes() throws Exception {
        var bookId = repository.save(new Book("name", "author", Publisher.PUBLISHER_A, 125, null, Language.UNKNOWN, null, null, false)).getId();
        var bookRequest = new Book("name 2", "author 2", Publisher.PUBLISHER_B, 100, BigDecimal.valueOf(123.55), Language.GE, "https://images-na.ssl-images-amazon.com/images/I/61ZKNw0xixL.jpg", OffsetDateTime.now(), true);
        var payload = objectMapper.writeValueAsString(bookRequest);
        mockMvc.perform(
                put("/books/" + bookId).contentType(MediaType.APPLICATION_JSON).content(payload)).
                andExpect(MockMvcResultMatchers.status().isOk());
        bookRequest.setId(bookId);
        var newBook = repository.findById(bookId).get();
        assertThat(objectMapper.writeValueAsString(newBook)).isEqualTo(objectMapper.writeValueAsString(bookRequest));
    }
}
