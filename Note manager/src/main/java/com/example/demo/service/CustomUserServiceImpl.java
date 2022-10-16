
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserModel;
import com.example.demo.repository.CustomUserRepository;  
@Service 
public  class CustomUserServiceImpl implements CustomUserService {   
	@Autowired
	private CustomUserRepository usrrep;  
    public UserModel saveuser(UserModel user)
    {

    	usrrep.save(user);
		return usrrep.findById(user.getId()).get();

    }
    
    public String userexist(String username) {
    	if(usrrep.existsByUserName(username)) {
		return "true";
    	}
    	else {
    		return "false";
    	}
    }

}
