package com.contactmanager.springboot.client;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.contactmanager.springboot.client.model.Contact;
import com.contactmanager.springboot.client.model.ContactDTO;
import com.contactmanager.springboot.client.model.Notes;
import com.contactmanager.springboot.client.repository.ContactRepository;
import com.contactmanager.springboot.client.repository.NotesRepository;
import com.contactmanager.springboot.client.service.ContactService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    // Mock properties of the following classes
    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private NotesRepository notesRepository;

    public ContactServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

//
//    @Test
//    public void testCreateContactWithNote() {
//        // Arrange
//        // Set up mocks and data
//        when(notesRepository.save(any(Notes.class))).thenReturn(new Notes(/* Initialize notes with data */));
//        when(contactRepository.save(any(Contact.class))).thenReturn(new Contact(/* Initialize contact with data */));
//
//        // Act
//        ContactDTO result = contactService.createContact("John", "Doe", "1234567890", "john@example.com", "Address", "Note");
//
//        // Assert
//        assertNotNull(result);
//    }

}