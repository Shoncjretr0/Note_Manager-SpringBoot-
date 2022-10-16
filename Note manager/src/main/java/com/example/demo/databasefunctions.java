package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.repository.CustomUserRepository;

@Component

public class databasefunctions  {
	@Autowired
    private static JdbcTemplate jdbcTemplate;
	private CustomUserRepository employeeRepository;
	
	
	public String register(String fname,String lname ,String username ,String password) {
	 
		    String s = null;
		    System.out.println("haha");
	        String sql = "INSERT INTO users (idusers, firstname, secondname, username, password) VALUES (?, ?, ?, ?, ?)";
	          
	        int no=(int) Math.random();
	        int result = jdbcTemplate.update(sql, no, fname, lname, username, password);
	         
	        if (result > 0) {
	            System.out.println("A new row has been inserted.");
	             s="true";
	        }
	        return s;
	         
	    }
	@SuppressWarnings("deprecation")
	public String logincheck(String userid,String pass)  {
		return pass;
		
		
//		 public List<user> findByFirstNamewithIndex(@PathVariable("firstname") String firstname,
//                 @PathVariable("department") String department){
//			return employeeRepository.findByFirstNamewithIndexParam(firstname,department);
//		
		
	}
	public String firstnotes(String notes,int createdby ) {
		String b = null;
		 String sql = "INSERT INTO notes (idnotes, notes, created by, created date, Last Modified by, Last Modified date ) VALUES (?, ?, ?, ?, ?, ?)";
         
	        int notesno=(int) Math.random();
	        LocalDate createdtime = LocalDate.now(); 
	        int result = jdbcTemplate.update(sql, notesno, notes, createdby, createdtime,createdby, createdtime);
	         
	        if (result > 0) {
	            System.out.println("A new row has been inserted.");
	             b="true";
	        }
	        else {
	        	b="false";
	        }
	        return b;
		
	}
	public String Editnotes(String notesid, String notes,int LastModifiedby ) {
		
		LocalDate modifiedtime = LocalDate.now();
		
		
		return "true";
		
		
		
		
	}
	
	

	

}
	

