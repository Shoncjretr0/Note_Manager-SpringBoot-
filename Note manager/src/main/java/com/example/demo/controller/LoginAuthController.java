package com.example.demo.controller;
import java.net.URI;
import java.util.List;


import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.databasefunctions;
import com.example.demo.config.JwtTokenUtil;
import com.example.demo.models.JwtResponse;
import com.example.demo.models.Loginmodel;
import com.example.demo.models.NotesModel;
import com.example.demo.models.SortModel;
import com.example.demo.models.UserModel;
import com.example.demo.repository.CustomUserRepository;
import com.example.demo.service.CustomNotesService;
import com.example.demo.service.CustomUserService;

import com.example.demo.service.PasswordCheck;    
@RestController 

public class LoginAuthController {
	
	
	@Autowired
    private databasefunctions dbfun;
	@Autowired
	private CustomUserRepository usrrep;  
	@Autowired
	private CustomUserService usrservice; 
	@Autowired
	private CustomNotesService cusnote;
	

		@Autowired
		private JwtTokenUtil jwtTokenUtil;

	
	
    @GetMapping("/")    
	public ModelAndView viewHome(Model model) {
		  
        Loginmodel home = new Loginmodel();
        model.addAttribute("home", home);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
       
    }
	
	@PostMapping(value="/save")    
	  public RedirectView submit(@ModelAttribute("home") Loginmodel home ,Model model) {
	   
//		authenticate(home.getUsername(), home.getPassword());
//
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//
//		final String token = jwtTokenUtil.generateToken(userDetails.getUsername());
//
//		return ResponseEntity.ok(new JwtResponse(token));
        
		String user=cusnote.tempauth(home.getUsername(),home.getPassword());
		 if (user=="true") {
			 return new RedirectView("/dashboard");
		   
		 }
		 else {
			 return new RedirectView("/passwordWrong");
		 }
		
	       
    }   
	 
		 
	    
	
	  @GetMapping("/register")
	    public ModelAndView welcome(Model model) {
		  UserModel user = new UserModel();
		  model.addAttribute("user", user);
		  ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("register");
	        return modelAndView;
	    }
	  
	  @PostMapping(value="/registersave")    
	  public RedirectView submit(@ModelAttribute("user") UserModel user ,Model model,RedirectAttributes attributes ) {
		  String value=usrservice.userexist(user.getUserName());
		  if (value=="false") {
			  System.out.println(user.getPassword());
			  System.out.println(user.getReTypePassword());
			  if(user.getPassword().equals(user.getReTypePassword())) {
				  
				  usrservice.saveuser(user); 
				 
			        return new RedirectView("/");
			    }
		
			  else {
				 
			        return new RedirectView("/PasswordNotMatch");
			    }
			}

			  
		  
		  
		  else {
			  
		        return new RedirectView("/Useridexist");
		  }
		
    } 
	  
	  @GetMapping("/dashboard")
	    public ModelAndView dashboard(Model model) {
		  List<NotesModel> notes = cusnote.getNotes();
	       model.addAttribute("notes", notes);
	       model.addAttribute("Sortnotes", new SortModel());
	   		ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("dashboard");
	        return modelAndView;
		 
	    }
	  
	  @GetMapping("/passwordWrong")    
		public ModelAndView Passwordwrong(Model model) {
			  
	        Loginmodel home = new Loginmodel();
	        model.addAttribute("home", home);
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("passwordWrong");
	        return modelAndView;
	       
	    }
	  
	  @GetMapping("/PasswordNotMatch")    
		public ModelAndView PasswordNotMatch(Model model) {
			  
	        Loginmodel home = new Loginmodel();
	        model.addAttribute("home", home);
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("PasswordNotMatch");
	        return modelAndView;
	       
	    }
	  
	  @GetMapping("/Useridexist")    
		public ModelAndView Useridexist(Model model) {
			  
	        Loginmodel home = new Loginmodel();
	        model.addAttribute("home", home);
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("UserExist");
	        return modelAndView;
	       
	    }
	  
	  
	  
	  
	  
	  	 
}
