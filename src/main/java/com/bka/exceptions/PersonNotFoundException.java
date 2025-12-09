package com.bka.exceptions;

import javax.persistence.EntityNotFoundException;

public class PersonNotFoundException extends EntityNotFoundException {
    public PersonNotFoundException(Long id) {
        super("Пользователь с ID " + id + " не найден");
    }
}