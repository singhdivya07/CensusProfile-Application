package com.capgemini.census.entity;
public class Login {
   
   // @NotNull(message="email must not be empty")
    private Integer Id;
   // @NotEmpty(message="Password must not be empty")
    private String pass;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}