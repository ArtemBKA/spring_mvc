//package com.bka.dao;
//
//import com.bka.entity.Person;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class PersonMapper implements RowMapper<Person> {
//    @Override
//    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
//        return Person.builder()
//                .id(resultSet.getLong("id"))
//                .firstname(resultSet.getString("firstname"))
//                .lastname(resultSet.getString("lastname"))
//                .build();
//    }
//}
