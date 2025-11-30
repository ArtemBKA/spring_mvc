package com.bka.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {
    private Long id;

    @NotBlank(message = "Firstname should not be blank")
    @Size(min = 2, max = 20, message = "Firstname should be between 2 and 20 symbols")
    private String firstname;

    @NotBlank(message = "Lastname should not be blank")
    @Size(min = 2, max = 20, message = "Lastname should be between 2 and 20 symbols")
    private String lastname;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Email should be valid")
    private String email;
}
