package com.bka.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Person {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
}
