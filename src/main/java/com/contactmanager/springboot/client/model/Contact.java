package com.contactmanager.springboot.client.model;

import java.util.Date;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {
	
	// Properties of the Contact Entity along with column name mappings
    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "phone_num")
    private String phoneNum;
    
    @Column(name = "email_addr")
    private String emailAddr;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "date_created")
    private Date dateCreated;

    // Composition: Each contact has a note object
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "notes_id")
    private Notes notes;
    
    
    // Default constructor
    public Contact() {
    }

    // Parameterized constructor
    public Contact(Long id, String firstName, String lastName, String email, String phoneNumber, String address) {
    	this.id = id;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.emailAddr = email;
    	this.phoneNum = phoneNumber;
    	this.address = address;
    	this.dateCreated = new Date();
    	this.notes = null;
    }
    
    public Contact(Long id, String firstName, String lastName, String email, String phoneNumber, String address, Notes note) {
    	this.id = id;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.emailAddr = email;
    	this.phoneNum = phoneNumber;
    	this.address = address;
    	this.dateCreated = new Date();
    	this.notes = note;
    }
    
    // Getters and setters
    public Long getId() {
    	return id;
    }

    public void setId(Long id) {
    	this.id = id;
    }

    public String getFirstName() {
    	return firstName;
    }

    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }

    public String getLastName() {
    	return lastName;
    }

    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

    public String getEmail() {
    	return emailAddr;
    }
    
    public void setEmail(String email) {
    	this.emailAddr = email;
	}
	
	public String getPhoneNumber() {
		return phoneNum;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNum = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDate(Date date) {
		this.dateCreated = date;
	}
	
	public Notes getNote() {
		return notes;
	}
	
	public void setNote(Notes note) {
		this.notes = note;
	}
}
