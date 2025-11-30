package com.bka.util;

import javax.validation.Validation;

import com.bka.dao.PersonDao;
import com.bka.dto.PersonDto;
import com.bka.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonDto dto = (PersonDto) o;
        if (personDao.getPersonByEmail(dto.getEmail()).isPresent()) {
            errors.rejectValue("email","","This email address is already in use");
        }
    }
}
