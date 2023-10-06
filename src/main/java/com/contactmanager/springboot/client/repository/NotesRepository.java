package com.contactmanager.springboot.client.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactmanager.springboot.client.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
	// Search for notes by contact ID and sort by date created
	List<Notes> findByContactIdOrderByDateCreatedDesc(Long contactId);
}
