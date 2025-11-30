package com.bka.dao;

import com.bka.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class PersonDao {
    JdbcTemplate template;
    @Autowired
    public PersonDao(JdbcTemplate template) {
        this.template = template;
    }


    public Person getPerson(Long id) {
        return template.query("select * from person where id = ?", new Object[]{id}
                        , new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Person> getAllPeople() {
        return template.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void addPerson(Person person) {
        template.update("insert into person(firstname, lastname, email, password) values(?, ?, ?, ?)"
                , person.getFirstname(), person.getLastname(), person.getEmail(), person.getPassword());
    }

    public void updatePerson(Long id, Person person) {
        template.update("update person set firstname=?, lastname=?, email=? where id=?", person.getFirstname(), person.getLastname(), person.getEmail(), id);
    }

    public void removePerson(Long id) {
        template.update("delete from person where id=?", id);
    }

    public void with(){
        List<Person> people = getOneThousenPeople();

        long start = System.currentTimeMillis();
        template.batchUpdate("INSERT INTO person (firstname, lastname, email, password) values (?, ?, ?, ?)"
                , new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    preparedStatement.setString(1, people.get(i).getFirstname());
                    preparedStatement.setString(2, people.get(i).getLastname());
                    preparedStatement.setString(3, people.get(i).getEmail());
                    preparedStatement.setString(4, people.get(i).getPassword());
                }

                @Override
                public int getBatchSize() {
                    return people.size();
                }
            });
        System.out.println(System.currentTimeMillis() - start);
    }

    public void without(){
        long start = System.currentTimeMillis();
        List<Person> people = getOneThousenPeople();
        people.forEach(this::addPerson);
        System.out.println(System.currentTimeMillis() - start);
    }

    private List<Person> getOneThousenPeople() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(
                    Person.builder()
                            .firstname("name_"+i)
                            .lastname("lastname_"+i)
                            .email(i+"aaa@email.com")
                            .password("password_111"+i)
                            .build()
            );
        }
        return people;
    }

    public Optional<Person> getPersonByEmail(String email) {
        return template.query("SELECT * FROM person where email=?"
                , new Object[]{email}
                , new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
}
