package com.moin.newsapp;

public enum Category {
    business("Business"),
    entertainment("Entertainment"),
    health("Health"),
    science("Science"),
    sports("Sports"),
    technology("Technology");

    private final String title;

    Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
