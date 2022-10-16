package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.NotesModel;
import com.example.demo.models.UserModel;

public interface CustomNotesService {
	
	public NotesModel addNotes(NotesModel notes);
	public List<NotesModel>  getNotes();
//	public NotesModel updateNotes(NotesModel notes,Integer id);
	public void deleteNotes(Integer id);
	public NotesModel getEmployeeById(Integer id);
	public List<NotesModel> sortCreatedBy(String created);
	public List<NotesModel> sortCreatedDate(String createddate);
	public List<NotesModel> sortModifiedBy(String modby);
	public List<NotesModel> sortModifiedDate(String moddate);
	public List<NotesModel> getsortCreatedDateUp();
	public List<NotesModel> getsortCreatedDateDown();
	public NotesModel updateNotes(NotesModel notes,int id);
	public String tempauth(String username,String password);
	public void MergeNotes(ArrayList<String> mergenotesid);
	
	

}
