package repository;


import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	// Custom queries using naming convention of JPQL.
	// Page and Pageable types used for pagination
	Page<Contact> findByName(String name, Pageable pageable);
	Page<Contact> findByEmail(String email, Pageable pageable);
	Page<Contact> findByNameIgnoreCaseContaining(String partialName, Pageable pageable); 		// Find contact by partial name
	Page<Contact> findByCreatedDateBefore(Date date, Pageable pageable);
	
	// Search contacts by partial note content
	@Query("SELECT C FROM Contact C, Notes N WHERE C.id = N.contact_id AND N.note_text LIKE %:noteText%")
	Page<Contact> findContactsWithNoteContaining(String noteText, Pageable pageable);			// Find contact by partial note text
}
