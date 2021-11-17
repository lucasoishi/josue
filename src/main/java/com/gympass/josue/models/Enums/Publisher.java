package com.gympass.josue.models.Enums;

public enum Publisher {
    PUBLISHER_A("Publisher A"),
    PUBLISHER_B("Publisher B"),
    PUBLISHER_C("Publisher C"),
    PUBLISHER_D("Publisher D");
    String publisher;
    Publisher(String publisher) {
        this.publisher = publisher;
    }
    public String toString() {
        return publisher;
    }
}
