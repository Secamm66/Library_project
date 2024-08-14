package com.example.project.dao;

import com.example.project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person ORDER BY person_id", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, year_of_birth) VALUES (?,?)",
                person.getName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, year_of_birth=? WHERE person_id=?",
                updatedPerson.getName(), updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    public Person getBookOwner(int book_id) { //Возвращает владельца книги на странице книги
        return jdbcTemplate.query("SELECT name FROM person JOIN book USING (person_id) WHERE book_id=?",
          new Object[]{book_id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> getPersonByName(String name) { // Валидация уникальности ФИО
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}