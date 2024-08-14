package com.example.project.models;


import javax.validation.constraints.*;

public class Book {

    private int bookId;
    private Integer personId;
    @NotEmpty(message = "Укажите название книги")
    @Size(min = 2, max = 100, message = "Название книги может содержать от 2 до 100 символов")
    private String title;
    @NotEmpty(message = "Укажите автора книги")
    @Size(min = 2, max = 100, message = "Имя автора может содержать от 2 до 100 символов")
    private String author;
    @NotNull(message = "Укажите год издательства книги")
    @Positive(message = "Значение должно быть больше 0")
    private Integer year;

    public Book(int bookId, int personId, String title, String author, Integer year) {
        this.bookId = bookId;
        this.personId = personId;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }


    public int getBookId() {
        return bookId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}