package com.example.project.dao;

import com.example.project.models.Book;
import com.example.project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT book_id, title, author, year FROM book ORDER BY book_id",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT book_id, person_id, title, author, year FROM book WHERE book_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title, author, year) VALUES (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=? WHERE book_id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public List<Book> getPersonHasBooks(int id) { //Возвращает список книг у человека
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void releaseBook(int id, Book releaseBook) { //Освобождает книгу
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", releaseBook.getPersonId(), id);
    }

    public void assignBook(int book_id, Person person) { //Назначает книгу пользователю
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?",
                person.getPersonId(), book_id);
    }
}
