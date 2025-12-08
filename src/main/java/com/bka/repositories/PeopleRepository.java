package com.bka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bka.entity.Person;

/**
 * @author Neil Alishev
 */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {

}