package model;

import java.time.LocalDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {
	
	// Fields of the Contact Entity along with column name mappings
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fname")
    private String firstName;
    
    @Column(name = "lname")
    private String lastName;
    
    @Column(name = "phone_num")
    private Long phoneNum;
    
    @Column(name = "email_addr")
    private String emailAddr;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    // Inheritance: Each contact has a note object
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    private Notes note;
}