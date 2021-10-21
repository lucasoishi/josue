package com.gympass.josue.controllers.contracts;

import java.util.List;

public class AuthorsRequest {
    private List<String> authors;

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
