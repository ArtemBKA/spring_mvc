package com.bka.service;

import com.bka.repositories.PeopleRepository;
import com.bka.exceptions.PersonNotFoundException;
import com.bka.dto.PersonDto;
import com.bka.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository repository;
    private final PasswordGeneratorService passwordGeneratorService;

    @Autowired
    public PeopleService(PeopleRepository repository, PasswordGeneratorService passwordGeneratorService) {
        this.repository = repository;
        this.passwordGeneratorService = passwordGeneratorService;
    }

    public List<PersonDto> getAllPeople() {
         List<Person> list = repository.findAll();
         return list.stream()
                 .map(this::personToDto)
                 .collect(Collectors.toList());
   }

    public PersonDto getPerson(Long id) {
        return repository.findById(id)
                .map(this::personToDto)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Transactional
    public void updatePerson(Long id, PersonDto updatedPerson) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        person.setFirstname(updatedPerson.getFirstname());
        person.setLastname(updatedPerson.getLastname());
        person.setEmail(updatedPerson.getEmail());
        repository.save(person);
    }

    @Transactional
    public void addPerson(PersonDto dto) {
        repository.save(dtoToPerson(dto));
    }

    @Transactional
    public void removePerson(Long id) {
        Person person = repository.findById(id).get();
        repository.delete(person);
    }

    private PersonDto personToDto(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .firstname(person.getFirstname())
                .lastname(person.getLastname())
                .email(person.getEmail())
                .build();
    }

    private Person dtoToPerson(PersonDto dto) {
        return Person.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .password(passwordGeneratorService.generatePassword())
                .build();
    }

}
