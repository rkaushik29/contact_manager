package repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import model.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long> {
	// Search for notes by contact ID and sort by date created
	List<Notes> findByContactIdOrderByCreatedDateDesc(Long contactId);
}
