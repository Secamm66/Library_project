package com.example.project.util;

import com.example.project.dao.PersonDAO;
import com.example.project.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (personDAO.getPersonByName(person.getName()).isPresent() &&
                person.getPersonId() != personDAO.getPersonByName(person.getName()).get().getPersonId())
            errors.rejectValue("name", "", "Пользователь с таким именем уже существует");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }
}
