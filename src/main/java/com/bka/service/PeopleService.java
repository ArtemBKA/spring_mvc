package com.bka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bka.entity.Person;
import com.bka.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(Long id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Long id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }
}



//package com.bka.service;
//
//import com.bka.dao.PersonDao;
//import com.bka.dto.PersonDto;
//import com.bka.entity.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class PeopleService {
//    private final PersonDao personDao;
//    private final PasswordGeneratorService passwordGeneratorService;
//
//    @Autowired
//    public PeopleService(PersonDao personDao, PasswordGeneratorService passwordGeneratorService) {
//        this.personDao = personDao;
//        this.passwordGeneratorService = passwordGeneratorService;
//    }
//
//    public List<PersonDto> getAllPeople() {
//         List<Person> list = personDao.getAllPeople();
//         return list.stream()
////                 .filter(p -> p.getPassword().contains("111"))
//                 .map(this::personToDto)
//                 .collect(Collectors.toList());
//   }
//
//    public PersonDto getPerson(Long id) {
//        Optional<Person> p = personDao.getPerson(id);
//        return personToDto(p.get());
//    }
//
//    public void updatePerson(Long id, PersonDto dto) {
//        personDao.updatePerson(id, dtoToPerson(dto));
//    }
//
//    public void addPerson(PersonDto dto) {
//        personDao.addPerson(dtoToPerson(dto));
//    }
//
//    public void removePerson(Long id) {
//        personDao.removePerson(id);
//    }
//
//    private PersonDto personToDto(Person person) {
//        return PersonDto.builder()
//                .id(person.getId())
//                .firstname(person.getFirstname())
//                .lastname(person.getLastname())
//                .email(person.getEmail())
//                .build();
//    }
//
//    private Person dtoToPerson(PersonDto dto) {
//        return Person.builder()
//                .firstname(dto.getFirstname())
//                .lastname(dto.getLastname())
//                .email(dto.getEmail())
//                .password(passwordGeneratorService.generatePassword())
//                .build();
//    }
//
//}
