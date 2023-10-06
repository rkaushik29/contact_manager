package com.contactmanager.springboot.client.repository;


import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contactmanager.springboot.client.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	// Custom queries using naming convention of JPQL.
	// Page and Pageable types used for pagination
	Page<Contact> findById(Long id, Pageable pageable);
	Page<Contact> findByEmail(String email, Pageable pageable);
	// Find contact by partial name by searchinf both first and last name for a match
	Page<Contact> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String fname, String lname, Pageable pageable);
	Page<Contact> findByDateCreatedBefore(Date date, Pageable pageable);
	Page<Contact> findAll(Pageable pageable);
	// Search note with partial text
	@Query("SELECT c FROM Contact c JOIN c.notes n WHERE LOWER(n.noteText) LIKE LOWER(CONCAT('%', :noteText, '%'))")
	Page<Contact> findContactsWithNoteContaining(@Param("noteText") String noteText, Pageable pageable);

}
