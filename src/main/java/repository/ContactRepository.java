package repository;


import org.springframework.data.jpa.repository.JpaRepository;
import model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	// CRUD operations
}
