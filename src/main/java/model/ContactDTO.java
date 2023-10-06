package model;

import java.util.Date;

public class ContactDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNum;
    private String emailAddr;
    private String address;
    private Date dateCreated;
    private NotesDTO notesDto;
    
    
    // Overloaded Constructors for varying input information
    public ContactDTO() {}
    
    public ContactDTO(String fname, String lname, String email, String phone, String addr, NotesDTO notesDto) {
        this.firstName = fname;
        this.lastName = lname;
        this.emailAddr = email;
        this.phoneNum = Long.valueOf(phone);
        this.address = addr;
        this.dateCreated = new Date();
        this.notesDto = notesDto;
    }
    
    public ContactDTO(String fname, String lname, String email, String phone, String addr) {
        this.firstName = fname;
        this.lastName = lname;
        this.emailAddr = email;
        this.phoneNum = null;
        this.address = addr;
        this.notesDto = new NotesDTO();
    }

    public ContactDTO(String fname, String lname, String email, String phone) {
        this.firstName = fname;
        this.lastName = lname;
        this.emailAddr = email;
        this.phoneNum = Long.valueOf(phone);
        this.address = "";
        this.notesDto = new NotesDTO();
    }
    
    
 // Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public NotesDTO getNoteDTO() {
        return notesDto;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setNote(NotesDTO noteDto) {
        this.notesDto = noteDto;
    }
}