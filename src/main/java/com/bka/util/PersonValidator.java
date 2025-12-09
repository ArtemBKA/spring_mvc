package com.bka.util;

import com.bka.dto.PersonDto;
import com.bka.repositories.PeopleRepository;
import com.bka.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.sql.DataSource;
import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleRepository repository;

    @Autowired
    public PersonValidator(PeopleRepository repository, DataSource dataSource) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PersonDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonDto dto = (PersonDto) o;
        Optional<Person> optionalPerson = repository.getPersonByEmail(dto.getEmail());
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            if (person.getId() == null || !person.getId().equals(dto.getId())) {
                errors.rejectValue("email","","This email address is already in use");
            }
        }
    }
}

