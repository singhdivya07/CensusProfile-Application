package com.capgemini.census.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class contains userId and password to log in to the application.
 * @author HP
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Login {
   
   // @NotNull(message="email must not be empty")
    private Integer userId;
   // @NotEmpty(message="Password must not be empty")
    private String password;
	
	
}