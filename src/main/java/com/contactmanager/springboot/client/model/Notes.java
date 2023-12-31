package com.contactmanager.springboot.client.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes")
public class Notes {
	
	// Properties of the Notes Entity
    @Id
    @Column(name = "notes_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contact_id")
    private Long contactId;
    
    @Column(name = "note_text")
    private String noteText;
    
    @Column(name = "date_created")
    private Date dateCreated;
    
    // Bidirectional relation between Contact and Note
    @OneToOne(mappedBy = "notes")
    private Contact contact;
    
    // Default Constructor
    public Notes() {
    	
    }
    
    // Parameterized Constructor
    public Notes(String noteText) {
    	this.contactId = contact.getId();
    	this.noteText = noteText;
    }
    
    // Getters and Setters
    public void setId(Long id) {
    	this.id = id;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setContactId(Long id) {
    	this.contactId = id;
    }
    
    public Long getContactId() {
    	return this.contactId;
    }
    
    public void setNoteText(String text) {
    	this.noteText = text;
    }
    
    public String getNoteText() {
    	return this.noteText;
    }
    
    public void setDate(Date date) {
    	this.dateCreated = date;
    }
    
    public Date getDate() {
    	return this.dateCreated;
    }
}