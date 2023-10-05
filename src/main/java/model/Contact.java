package model;

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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fname")
    private String firstName;
    
    @Column(name = "lname")
    private String lastName;
    
    @Column(name = "phone_num")
    private Long phoneNum;
    
    @Column(name = "email_addr")
    private String emailAddr;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "date_created")
    private Date dateCreated;

    // Composition: Each contact has a note object
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    private Notes note;
    
    
    // Default constructor
    public Contact() {
    }

    // Parameterized constructor
    public Contact(Long id, String firstName, String lastName, String email, String phoneNumber, String address) {
    	this.id = id;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.emailAddr = email;
    	this.phoneNum = Long.valueOf(phoneNumber);	// Phone could be a string from the form, convert here
    	this.address = address;
    	this.dateCreated = new Date();
    }
    
    // Getters and setters
    public Long getId() {
    	return this.id;
    }

    public void setId(Long id) {
    	this.id = id;
    }

    public String getFirstName() {
    	return this.firstName;
    }

    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }

    public String getLastName() {
    	return this.lastName;
    }

    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

    public String getEmail() {
    	return this.emailAddr;
    }
    
    public void setEmail(String email) {
    	this.emailAddr = email;
	}
	
	public String getPhoneNumber() {
		return Long.toString(this.phoneNum);
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNum = Long.valueOf(phoneNumber);
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getDateCreated() {
		return this.dateCreated;
	}
}