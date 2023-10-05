package repository;


import org.springframework.data.jpa.repository.JpaRepository;
import model.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long> {
	// CRUD operations
}
