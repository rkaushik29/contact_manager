package model;

import java.util.Date;

public class NotesDTO {
    private Long id;
    private Long contactId;
    private String noteText;
    private Date dateCreated;

    public NotesDTO() {
    }

    public NotesDTO(Long id, Long contactId, String noteText, Date dateCreated) {
        this.id = id;
        this.contactId = contactId;
        this.noteText = noteText;
        this.dateCreated = dateCreated;
    }

 // Getters
    public Long getId() {
        return id;
    }

    public Long getContactId() {
        return contactId;
    }

    public String getNoteText() {
        return noteText;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}