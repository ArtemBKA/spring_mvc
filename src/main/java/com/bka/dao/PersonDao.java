package com.bka.dao;

import com.bka.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PersonDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public Optional<Person> getPerson(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.of(session.get(Person.class, id));
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPeople() {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("FROM Person", Person.class);
        return query.getResultList();
    }

    @Transactional
    public Long addPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(person);
    }

    @Transactional
    public void updatePerson(Long id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person p = session.get(Person.class, id);
        if (p != null) {
            p.setFirstname(person.getFirstname());
            p.setLastname(person.getLastname());
            p.setEmail(person.getEmail());
        }
    }

    @Transactional
    public void removePerson(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Person p = session.get(Person.class, id);
        if (p != null) {
            session.delete(p);
        }
    }


    @Transactional(readOnly = true)
    public Optional<Person> getPersonByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("from Person where email = :email", Person.class);
        query.setParameter("email", email);
        return query.uniqueResultOptional();
    }
}