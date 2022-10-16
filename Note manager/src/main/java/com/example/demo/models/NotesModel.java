package com.example.demo.models;


import javax.persistence.Entity;

import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity  
@Table(name = "notes" )
public class NotesModel {
	
	
	@Id	
    @Column(name="idnotes")
	@GeneratedValue
	private Integer notesid;
	@Column(name="notes")
	private String  notes;
	@Column(name="created by")
	private String createdby;
	@Temporal(TemporalType.DATE)
	@Column(name="created date")
	private Date  createddate;
	@Column(name="Last Modified by")
	private String lastmodifiedby;
	@Column(name="Last Modified date")
	@Temporal(TemporalType.DATE)
	private Date lastmodifieddate;
	
	
	@PrePersist
	private void onCreate() {
		createddate = new Date();
		lastmodifieddate= new Date();
	}
	
	
	public Integer getNotesid() {
		return notesid;
	}
	public void setNotesid(Integer notesid) {
		this.notesid = notesid;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public String getLastmodifiedby() {
		return lastmodifiedby;
	}
	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}
	public Date getLastmodifieddate() {
		return lastmodifieddate;
	}
	public void setLastmodifieddate(Date lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}
	
	
	
	
	
	

}
