package com.example.demo.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Loginmodel {
	
	@NotBlank(message = "Username is mandatory")
	private String  username;
	@NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 15)
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
