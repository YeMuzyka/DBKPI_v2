package ru.kle10wka.table;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "room")
public class Room {
	
	@Id
	@NotNull
	@Size(max = 9)
	@Column(name = "id_room")
	private String id_room;
	
	@Column(name = "max_in_room")
	private int max_in_room;
	
	@Column(name = "free_in_room")
	private int free_in_room;
	
	@Column(name = "male_female")
	private String male_female;
	
	@ManyToOne
	@JoinColumn(name = "id_bloc")
	private Bloc id_bloc;
	
	@OneToMany(mappedBy = "inhabited_room")
	private Set<InhabitedStudent> inhabited_room = new HashSet<InhabitedStudent>();
	
	public Room(){
		
	}
	
	public Room(String id_room){
		this.id_room = id_room;
	}

	public String getId_room() {
		return id_room;
	}

	public void setId_room(String id_room) {
		this.id_room = id_room;
	}

	public int getMax_in_room() {
		return max_in_room;
	}

	public void setMax_in_room(int max_in_room) {
		this.max_in_room = max_in_room;
	}

	public int getFree_in_room() {
		return free_in_room;
	}

	public void setFree_in_room(int free_in_room) {
		this.free_in_room = free_in_room;
	}

	public String getMale_female() {
		return male_female;
	}

	public void setMale_female(String male_female) {
		this.male_female = male_female;
	}

	public Bloc getId_bloc() {
		return id_bloc;
	}

	public void setId_bloc(Bloc id_bloc) {
		this.id_bloc = id_bloc;
	}

	public Set<InhabitedStudent> getInhabited_room() {
		return inhabited_room;
	}

	public void setInhabited_room(Set<InhabitedStudent> inhabited_room) {
		this.inhabited_room = inhabited_room;
	}
	
}