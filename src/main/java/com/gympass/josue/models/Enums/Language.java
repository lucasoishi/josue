package com.gympass.josue.models.Enums;

public enum Language {
    PT("portuguese"),
    EN("english"),
    KR("korean"),
    JP("japanese"),
    ES("spanish"),
    GE("german"),
    FR("french"),
    UNKNOWN("language not provided");
    String language;

    Language(String language) {
        this.language = language;
    }

    public String toString() {
        return language;
    }
}
