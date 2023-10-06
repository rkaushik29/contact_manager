package com.contactmanager.springboot.client.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.contactmanager.springboot.client.repository.ContactRepository;
import com.contactmanager.springboot.client.repository.NotesRepository;

import jakarta.persistence.EntityNotFoundException;
import com.contactmanager.springboot.client.model.Contact;
import com.contactmanager.springboot.client.model.ContactDTO;
import com.contactmanager.springboot.client.model.Notes;
import com.contactmanager.springboot.client.model.NotesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.contactmanager.springboot.client.repository.ContactRepository;
import com.contactmanager.springboot.client.repository.NotesRepository;

import java.util.Date;


@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private NotesRepository notesRepository;
    
    // Show contact by partial name search, searching first and last name.
    public Page<ContactDTO> showContactByPartialName(String partialName, Pageable pageable) throws NotFoundException {
        Page<Contact> contacts = contactRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(partialName, partialName, pageable);

        if (contacts.isEmpty()) {
            throw new NotFoundException();
        }

        // Return a mapping from contact to the DTO for Pages
        return contacts.map(contact -> new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmail(), contact.getAddress(), contact.getDateCreated(), new NotesDTO(contact.getNote())));
    }

    // Create a new Contact with Note
    public ContactDTO createContact(String firstName, String lastName, String phoneNum, String emailAddr, String address, String noteText) {
        // Validation checks
        if (firstName == null || lastName == null || phoneNum == null || emailAddr == null || address == null || noteText == null) {
            throw new IllegalArgumentException("All fields must be filled");
        }
        
        if (!emailAddr.contains("@")) {
            throw new IllegalArgumentException("Email address is not valid");
        }

        // Create a new Contact entity
        Contact newContact = new Contact();
        Notes newNote = new Notes();
        newContact.setFirstName(firstName);
        newContact.setLastName(lastName);
        newContact.setPhoneNumber(phoneNum);
        newContact.setEmail(emailAddr);
        newContact.setAddress(address);
        newContact.setDate(new Date());
        
        // New note
        newNote.setNoteText(noteText);
        newNote.setDate(new Date());
        newNote.setContactId(newContact.getId());

        // Save to the database via repository
        Notes savedNote = notesRepository.save(newNote);
        newContact.setNote(savedNote);
        Contact savedContact = contactRepository.save(newContact);

        // Transform entity to DTO
        ContactDTO savedContactDTO = new ContactDTO(savedContact.getId(), savedContact.getFirstName(), savedContact.getLastName(), savedContact.getPhoneNumber(), 
        		savedContact.getEmail(), savedContact.getAddress(), savedContact.getDateCreated(), new NotesDTO(savedNote));

        // Confirmation - Log after saving to db
        System.out.println("New contact has been saved with ID: " + savedContact.getId());
        
        return savedContactDTO;
    }

    // Create a new Contact without Note
    public ContactDTO createContact(String firstName, String lastName, String phoneNum, String emailAddr, String address) {
        // Validation checks
        if (firstName == null || lastName == null || phoneNum == null || emailAddr == null || address == null) {
            throw new IllegalArgumentException("All fields must be filled");
        }
        
        if (!emailAddr.contains("@")) {
            throw new IllegalArgumentException("Email address is not valid");
        }

        // Create a new Contact entity
        Contact newContact = new Contact();
        newContact.setFirstName(firstName);
        newContact.setLastName(lastName);
        newContact.setPhoneNumber(phoneNum);
        newContact.setEmail(emailAddr);
        newContact.setAddress(address);
        newContact.setDate(new Date());

        // Save to the database via repository
        Contact savedContact = contactRepository.save(newContact);

        // Transform entity to DTO
        ContactDTO savedContactDTO = new ContactDTO(savedContact.getId(), savedContact.getFirstName(), savedContact.getLastName(), savedContact.getPhoneNumber(), savedContact.getEmail(), savedContact.getAddress(), savedContact.getDateCreated());

        // Confirmation - Log after saving to db
        System.out.println("New contact has been saved with ID: " + savedContact.getId());
        
        return savedContactDTO;
    }
    
    // Show all contacts
    public Page<ContactDTO> showAllContacts(Pageable pageable) throws NotFoundException {
        Page<Contact> contacts = contactRepository.findAll(pageable);
        
        if (contacts.isEmpty()) {
            throw new NotFoundException();
        }

        // Return a mapping from contact to the DTO for Pages
        return contacts.map(contact -> new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmail(), contact.getAddress(), contact.getDateCreated(), new NotesDTO(contact.getNote(), contact.getId())));
    }
    
    // Method to fetch current details
    public ContactDTO getCurrentContact(Long contactId) {
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if (!optionalContact.isPresent()) {
            throw new EntityNotFoundException("Contact with ID " + contactId + " not found.");
        }
        
        Contact existingContact = optionalContact.get();
        Notes existingNotes = existingContact.getNote();
        return new ContactDTO(existingContact, new NotesDTO(existingNotes));
    }
    
    // Method to edit contact details
    public ContactDTO editContact(ContactDTO contact) {
        Optional<Contact> optionalContact = contactRepository.findById(contact.getId());
        if (!optionalContact.isPresent()) {
            throw new EntityNotFoundException("Contact with name: " + contact.getFirstName() + " " + contact.getLastName() + " not found.");
        }

        // Edit contact and note details
        Contact existingContact = optionalContact.get();
        Notes existingNotes = existingContact.getNote();
        
        existingContact.setFirstName(contact.getFirstName() != null ? contact.getFirstName() : existingContact.getFirstName());
        existingContact.setLastName(contact.getLastName() != null ? contact.getLastName() : existingContact.getLastName());
        existingContact.setPhoneNumber(contact.getPhoneNum() != null ? contact.getPhoneNum() : (existingContact.getPhoneNumber()));
        existingContact.setEmail(contact.getEmailAddr() != null ? contact.getEmailAddr() : existingContact.getEmail());
        existingContact.setAddress(contact.getAddress() != null ? contact.getAddress() : existingContact.getAddress());
        existingNotes.setNoteText(contact.getNoteDTO().getNoteText() != null ? contact.getNoteDTO().getNoteText() : existingNotes.getNoteText());
        existingNotes.setDate(new Date()); // Update the date created to the current date

        // Save edited details
        notesRepository.save(existingNotes);
        Contact savedContact = contactRepository.save(existingContact);

        // Transform entities to DTO
        ContactDTO savedContactDTO = new ContactDTO(savedContact, new NotesDTO(existingNotes));

        // Confirmation message
        System.out.println("Contact and note with ID " + savedContact.getId() + " and " + existingNotes.getId() + " have been updated.");

        return savedContactDTO;
    }
    
    // Method to delete contact after confirmation
    public void deleteContact(Long contactId) {
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if (!optionalContact.isPresent()) {
            throw new EntityNotFoundException("Contact with ID " + contactId + " not found.");
        }
        contactRepository.deleteById(contactId);
        // Confirmation message
        System.out.println("Contact with ID " + contactId + " has been deleted.");
    }

}
