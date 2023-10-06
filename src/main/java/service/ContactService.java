package service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import model.Contact;
import model.ContactDTO;
import model.Notes;
import model.NotesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.ContactRepository;
import repository.NotesRepository;

import java.util.Date;


@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    private NotesRepository notesRepository;
    
    // Show contact by partial name search, searching first and last name.
    public Page<ContactDTO> showContactByPartialName(String partialName, Pageable pageable) throws NotFoundException {
        Page<Contact> contacts = contactRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(partialName, partialName, pageable);

        if (contacts.isEmpty()) {
            throw new NotFoundException();
        }

        // Return a mapping from contact to the DTO for Pages
        return contacts.map(contact -> new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(), Long.toString(contact.getPhoneNumber()), contact.getEmail(), contact.getAddress(), contact.getDateCreated(), new NotesDTO(contact.getNote())));
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
        ContactDTO savedContactDTO = new ContactDTO(savedContact.getId(), savedContact.getFirstName(), savedContact.getLastName(), Long.toString(savedContact.getPhoneNumber()), 
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
        ContactDTO savedContactDTO = new ContactDTO(savedContact.getId(), savedContact.getFirstName(), savedContact.getLastName(), Long.toString(savedContact.getPhoneNumber()), savedContact.getEmail(), savedContact.getAddress(), savedContact.getDateCreated());

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
        return contacts.map(contact -> new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(), Long.toString(contact.getPhoneNumber()), contact.getEmail(), contact.getAddress(), contact.getDateCreated()));
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
    public ContactDTO editContact(Long contactId, String newFirstName, String newLastName, String newPhoneNum, String newEmailAddr, String newAddress, String newNoteText) {
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if (!optionalContact.isPresent()) {
            throw new EntityNotFoundException("Contact with ID " + contactId + " not found.");
        }

        // Edit contact and note details
        Contact existingContact = optionalContact.get();
        Notes existingNotes = existingContact.getNote();
        
        existingContact.setFirstName(newFirstName != null ? newFirstName : existingContact.getFirstName());
        existingContact.setLastName(newLastName != null ? newLastName : existingContact.getLastName());
        existingContact.setPhoneNumber(newPhoneNum != null ? newPhoneNum : Long.toString(existingContact.getPhoneNumber()));
        existingContact.setEmail(newEmailAddr != null ? newEmailAddr : existingContact.getEmail());
        existingContact.setAddress(newAddress != null ? newAddress : existingContact.getAddress());
        existingNotes.setNoteText(newNoteText != null ? newNoteText : existingNotes.getNoteText());
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