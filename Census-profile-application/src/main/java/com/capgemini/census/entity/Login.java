package com.capgemini.census.entity;


/**
 * This class contains userId and password to log in to the application.
 * @author HP
 *
 */
public class Login {
   
   // @NotNull(message="email must not be empty")
    private Integer userId;
   // @NotEmpty(message="Password must not be empty")
    private String password;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}