package com.example.demo.service;


import com.example.demo.models.UserModel;

public interface CustomUserService {
	
	public UserModel saveuser(UserModel user);

	public String userexist(String username);
	

}
