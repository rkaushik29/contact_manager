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
public class NotesService {
	
	
}