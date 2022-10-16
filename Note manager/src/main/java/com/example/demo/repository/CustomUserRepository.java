package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.models.UserModel;


public interface CustomUserRepository extends JpaRepository<UserModel, Integer>{
	
	 boolean existsByUserName(String userName );

	 UserModel findByuserName(String userName);
	

}
