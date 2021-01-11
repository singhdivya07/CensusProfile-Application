package com.capgemini.census.entity;

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
   private Integer userId;
   private String password;
	
}