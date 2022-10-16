package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserModel;

 
@Service
public class PasswordCheck {
	

	UserModel user=new UserModel();
	
	public  String Validate() {
		 if(user.getPassword().equals(user.getReTypePassword())) {
			 return "true";
		 }
		 else {
			 return "false";
		 }
	}

}
