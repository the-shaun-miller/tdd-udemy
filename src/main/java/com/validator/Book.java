package com.validator;

public class Book {

    private String isbn;
    private String title;
    private String authorLastName;

    public Book(String isbn, String title, String authorLastName) {
        this.isbn = isbn;
        this.title = title;
        this.authorLastName = authorLastName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

}
