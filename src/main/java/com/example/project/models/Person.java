package com.example.project.models;


import javax.validation.constraints.*;

public class Person {
    private int personId;
    @NotEmpty(message = "Укажите ФИО читателя")
    @Size(min = 8, max = 100, message = "Имя может содержать от 8 до 100 символов")
    @Pattern(regexp =  "^[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+\\s[А-ЯЁ][а-яё]+$",
            message = "ФИО необходимо указывать в формате: Иванов Иван Иванович")
    private String name;
    @NotNull(message = "Укажите год рождения")
    @Min(value = 1900, message = "Некорректно указан год рождения")
    @Max(value = 2024, message = "Некорректно указан год рождения")
    private Integer yearOfBirth;

    public Person(int personId, String name, Integer yearOfBirth) {
        this.personId = personId;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
