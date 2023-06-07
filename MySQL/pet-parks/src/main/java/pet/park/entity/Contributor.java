package pet.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// cmd space for box
@Table
@Entity
@Data
public class Contributor { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contributorId;
	private String contributorName;
	
	@Column(unique = true)
	private String contributorEmail;
	
	// one to many relationship
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL) // contributor found in pet park, given name of java variable
	private Set<PetPark> petParks = new HashSet<>(); 
}
