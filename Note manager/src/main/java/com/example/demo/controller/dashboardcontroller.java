package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.models.NotesModel;
import com.example.demo.models.SortModel;
import com.example.demo.models.UserModel;
import com.example.demo.service.CustomNotesService;
import com.example.demo.service.CustomNotesServiceImpl;
import com.example.demo.service.CustomUserService;


@RestController 
public class dashboardcontroller {
	
	

	public String updateid;
	@Autowired
	private CustomNotesService notesservice; 
	@Autowired
	private CustomNotesServiceImpl cusimpl;

    @Value(value = "")
    private String url; 
	
	@GetMapping("/dashboard/addnotes")
    public ModelAndView AddNotes(Model model) {
	  NotesModel notes = new NotesModel();
	  model.addAttribute("Notes", notes);
	  ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("add");
      return modelAndView;
    }
	
	  @PostMapping(value="/dashboard/savenotes")    
	  public RedirectView submit(@ModelAttribute("Notes") NotesModel notes,Model model ) {
		  notes.setCreatedby("sggs");
		  notes.setLastmodifiedby("ajajaj");
		  System.out.println(notes.getNotesid());
		  System.out.println(notes.getNotes());
		  System.out.println(notes.getCreatedby());
		  System.out.println(notes.getCreateddate());
		  System.out.println(notes.getLastmodifiedby());
		  System.out.println(notes.getLastmodifieddate());
		  notesservice.addNotes(notes); 
		  return new RedirectView("/dashboard");
		
    } 
	
	
	@GetMapping("/dashboard/Update/{id}")
    public ModelAndView EditNotes( @PathVariable(value = "id") Integer id,Model model) {
	  NotesModel notes = new NotesModel();
	  notes = notesservice.getEmployeeById(id);
	  System.out.println(notes.getNotes());
	  System.out.println(notes.getNotesid());
	  model.addAttribute("Editnotes", notes);
	  ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        return modelAndView;
    }
	
	@GetMapping("/dashboard/MergeCount/{id}")
    public RedirectView MergeCount( @PathVariable(value = "id") String id,Model model) {
	  
		ArrayList<String> mergenotesid = new ArrayList<>(Arrays.asList(id.split(",")));
		notesservice.MergeNotes(mergenotesid);
		return new RedirectView("/dashboard");
    
	 
	
    }
	
	
	
	 @GetMapping("/dashboard/deleteEmployee/{id}")
	    public RedirectView deleteEmployee(@PathVariable(value = "id") Integer id,Model model) {
	        this.notesservice.deleteNotes(id);
	        return new RedirectView("/dashboard");
	    }
	 
	 @PostMapping(value="/dashboard/editNotes/{id}")
	    public RedirectView saveEditNotes(@ModelAttribute("Editnotes") NotesModel notes,@PathVariable(value = "id")Integer id) {
		
		 System.out.println(notes.getNotes());
		 System.out.println(notes.getNotesid());
		 notesservice.updateNotes( notes,id);
		 return new RedirectView("/dashboard");
	        
	    }
	 
	 @PostMapping(value="/dashboard/sortNotes")
	  public ModelAndView sortNotes(@ModelAttribute("Sortnotes") SortModel sort,Model model) {
		    System.out.println(sort.getSortData());
		    System.out.println(sort.getSortValue());
		    if (sort.getSortValue()==1) {
			List<NotesModel> notes = notesservice.sortCreatedBy(sort.getSortData());
			model.addAttribute("notes", notes);
		    }
		    else if(sort.getSortValue()==2) {
		    	
		    	List<NotesModel> notes = notesservice.sortCreatedDate(sort.getSortData());
				model.addAttribute("notes", notes);
		    	
		    	
		    }
		    else if(sort.getSortValue()==3) {
		    	
		    	List<NotesModel> notes = notesservice.sortModifiedBy(sort.getSortData());
				model.addAttribute("notes", notes);
		    	
		    }
		    else if(sort.getSortValue()==4) {
		    	
		    	List<NotesModel> notes = notesservice.sortModifiedDate(sort.getSortData());
				model.addAttribute("notes", notes);
		    	
		    }
		    else {
		    	
		    	 List<NotesModel> notes = notesservice.getNotes();
			       model.addAttribute("notes", notes);
		    	
		    }
			ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("dashboard");
	        return modelAndView;  

	        
	    }
	 
	 @GetMapping(value="/dashboard/sortCreatedDateUp")
	  public ModelAndView sortCreatedDateUp(Model model) {
		   
		    
			List<NotesModel> notes = notesservice.getsortCreatedDateUp();
			
			model.addAttribute("notes", notes);
		    model.addAttribute("Sortnotes", new SortModel());
		    ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("dashboard");
	        return modelAndView;  

	        
	    }
	 @GetMapping(value="/dashboard/sortCreatedDateDown")
	  public ModelAndView sortCreatedDateDown( Model model) {
		   
		    
			List<NotesModel> notes = notesservice.getsortCreatedDateDown();
			
			 model.addAttribute("notes", notes);
		       model.addAttribute("Sortnotes", new SortModel());
		    ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("dashboard");
	        return modelAndView;  

	        
	    }
	

	

}
