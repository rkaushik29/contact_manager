package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.micrometer.core.instrument.config.validate.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import model.Contact;
import model.ContactDTO;
import model.NotesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.ContactRepository;
import java.util.Optional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    
    // Show contact by partial name search, searching first and last name.
    public Page<ContactDTO> showContactByPartialName(String partialName, Pageable pageable) throws NotFoundException {
        Page<Contact> contacts = contactRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(partialName, partialName, pageable);

        if (contacts.isEmpty()) {
            throw new NotFoundException();
        }

        // Return a mapping from contact to the DTO for Pages
        return contacts.map(contact -> new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmail(), contact.getAddress(), contact.getDateCreated()));
    }

    // Create a new Contact
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
        return contacts.map(contact -> new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmail(), contact.getAddress(), contact.getDateCreated()));
    }
    
    // Similar methods for update and delete
}
