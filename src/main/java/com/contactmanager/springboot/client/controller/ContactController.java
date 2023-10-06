package com.contactmanager.springboot.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.EntityNotFoundException;
import com.contactmanager.springboot.client.model.ContactDTO;
import com.contactmanager.springboot.client.model.NotesDTO;
import com.contactmanager.springboot.client.service.ContactService;

// Controller for handling Contact Requests. Allows CORS from port 8081 for integration test with FE
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/contacts")
public class ContactController {
	
	@Autowired
	ContactService contactService;
    
	// Endpoint to add a new contact
    @PostMapping("/add")
    public ResponseEntity<String> addContact(
            @RequestParam String firstName, 
            @RequestParam String lastName,
            @RequestParam String phoneNum,
            @RequestParam String emailAddr,
            @RequestParam String addr,
            @RequestParam(required = false) String noteText) {

        try {
        	// Optional noteText is allows due to service method overloading
        	if (noteText == null) {
        		ContactDTO contactDTO = contactService.createContact(firstName, lastName, phoneNum, emailAddr, addr);
        	} else {
        		ContactDTO contactDTO = contactService.createContact(firstName, lastName, phoneNum, emailAddr, addr, noteText);
        	}
            
            return new ResponseEntity<>("Contact added successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to update a contact
    @PutMapping("/update")
    public ResponseEntity<String> updateContact(
            @RequestParam String contactId,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String phoneNum,
            @RequestParam String emailAddr,
            @RequestParam String addr,
            @RequestParam(required = false) String noteText) {

        try {
            ContactDTO existingContact = contactService.getCurrentContact(Long.valueOf(contactId));
            System.out.println("got the existing contact");
            NotesDTO existingNote = existingContact.getNoteDTO();
            // Here, you could return existingContact to the frontend for user confirmation before updating
            if (noteText != null) {
            	existingNote.setNoteText(noteText);
            }
            ContactDTO updatedContact = new ContactDTO(Long.valueOf(contactId), firstName, lastName, phoneNum, emailAddr, addr, existingContact.getDateCreated(), existingNote);
            contactService.editContact(updatedContact);
            return new ResponseEntity<>("Contact updated successfully.", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Contact not found.", HttpStatus.NOT_FOUND);
        }
    }
    
    // Endpoint to show all contacts
    @GetMapping("/all")
    public Page<ContactDTO> getAllContacts(@RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "10") int size,
                                           @RequestParam(name = "sort", defaultValue = "id") String sort) throws NotFoundException {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return contactService.showAllContacts(pageable);
    }
	
	// Endpoint to fetch contact details for confirmation
    @GetMapping("/confirm-delete/{contactId}")
    public ResponseEntity<ContactDTO> fetchContactForDeletion(@PathVariable Long contactId) {
        try {
            ContactDTO contactDTO = contactService.getCurrentContact(contactId);
            return new ResponseEntity<>(contactDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Endpoint to delete a contact after confirmation
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteContact(@RequestParam String contactId) {
        try {
            contactService.deleteContact(Long.valueOf(contactId));
            return new ResponseEntity<>("Contact deleted successfully.", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Contact not found.", HttpStatus.NOT_FOUND);
        }
    }
}