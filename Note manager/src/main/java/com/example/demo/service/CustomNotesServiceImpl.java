package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;

import javax.swing.text.DateFormatter;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.models.JwtResponse;
import com.example.demo.models.NotesModel;
import com.example.demo.models.UserModel;
import com.example.demo.repository.CustomNotesRepository;
import com.example.demo.repository.CustomUserRepository;

@Service
@Transactional
public class CustomNotesServiceImpl implements CustomNotesService {

	@Autowired
	private CustomNotesRepository notesrep;
	@Autowired
	private CustomUserRepository userrep;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private JwtTokenUtil jwttoken;

	public NotesModel addNotes(NotesModel notes) {
		
		

		notesrep.save(notes);

		return null;
	}

	public List<NotesModel> getNotes() {
		return notesrep.findAll();
	}

	@Override
	public NotesModel updateNotes(NotesModel notes,int id) {
		NotesModel notesFromDB = notesrep.findById(id).get();
		 Date date = new Date();  
		notesFromDB.setLastmodifieddate(date);
		
		notesFromDB.setNotes(notes.getNotes());
		return notesrep.save(notesFromDB);

	}


	public void deleteNotes(Integer id) {
		notesrep.deleteById(id);
	}

	public NotesModel getEmployeeById(Integer id) {
		Optional<NotesModel> optional = notesrep.findById(id);
		NotesModel notes = null;
		if (optional.isPresent()) {
			notes = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return notes;

	}

	public List<NotesModel> sortCreatedBy(String createdbyy) {

		String sql = "SELECT * FROM notes WHERE `created by` ='" + createdbyy + "';";
		System.out.println(sql);
		List<NotesModel> notes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(NotesModel.class));
		System.out.println(notes);
		return notes;

	}

	public List<NotesModel> sortCreatedDate(String createddate) {

		String sql = "SELECT * FROM notes WHERE `created date` ='" + createddate + "';";
		System.out.println(sql);
		List<NotesModel> notes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(NotesModel.class));
		System.out.println(notes);
		return notes;

	}

	public List<NotesModel> sortModifiedBy(String modby) {

		String sql = "SELECT * FROM notes WHERE `last modified by` ='" + modby + "';";
		System.out.println(sql);
		List<NotesModel> notes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(NotesModel.class));
		System.out.println(notes);
		return notes;

	}

	public List<NotesModel> sortModifiedDate(String moddate) {

		String sql = "SELECT * FROM notes WHERE `last modified date` ='" + moddate + "';";
		System.out.println(sql);
		List<NotesModel> notes = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(NotesModel.class));
		System.out.println(notes);
		return notes;

	}

	public List<NotesModel> getsortCreatedDateUp() {

		return notesrep.findAll(Sort.by(Sort.Direction.ASC, "createddate"));

	}

	public List<NotesModel> getsortCreatedDateDown() {

		return notesrep.findAll(Sort.by(Sort.Direction.DESC, "createddate"));

	}

//		@Transactional
//		public NotesModel updateNotes(NotesModel notes, Integer id) {
//			System.out.println(id);
//			notesrep.save(notes);
//			return null;
//		}
//		

	public String update(String notes, Integer id) {

		String sql = "UPDATE notes SET notes = " + notes + "WHERE idnotes=" + id + ";";
		System.out.println(sql);
		jdbcTemplate.execute(sql);
		return "data inserted Successfully";

	}
      
	    @Override
		public String tempauth(String username,String Password) {
			
			UserModel userr = new UserModel();
			
			System.out.println(username);
			System.out.println(Password);
//			userr= (UserModel) userrep.findByuserName(username);
			if(userrep.existsByUserName(username)) {
				userr= (UserModel) userrep.findByuserName(username);
			}
			else {
				return "false";
			}
			 
			
		
			  
	           if(userr.getPassword().toString().equals(Password)) {
		    	  
		    	  System.out.println("true");
		    	  String token = jwttoken.generateToken(username);
				  System.out.println(token);
				  JwtResponse jwt=new JwtResponse();
				  jwt.setJwttoken(token);
		    	  return "true";
		    	  
		      }
		      else if(userr.getUserName().equals(null)){
		    	  
		    	  System.out.println("flase");
		    	  return "false";
		      }
		      else {
		    	  return "false";
		      }
			  
			
			  
		}
		
		public void MergeNotes(ArrayList<String> mergenotesid) {
			
			
			System.out.println(mergenotesid.size());
			
			for(int i=0;i<mergenotesid.size();i++) {
				
				if(i+1 < mergenotesid.size()) {
				
				NotesModel notesFind = notesrep.findById(Integer.parseInt(mergenotesid.get(i+1))).get();	
				
				
				NotesModel notesUpdate = notesrep.findById(Integer.parseInt(mergenotesid.get(0))).get();
				notesUpdate.setNotes(notesUpdate.getNotes()+ notesFind.getNotes());
				
				notesrep.deleteById(Integer.parseInt(mergenotesid.get(i+1)));
				
				
				
				
				}
				
			}
			
			
		}

		
}
