package com.example.demo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableJpaRepositories
public class NoteManagerApplication  {
	@Autowired
	public static  databasefunctions dbfun;
	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(NoteManagerApplication.class, args);
		

			
	}

	
	  
	    
	  
	  
     

}
