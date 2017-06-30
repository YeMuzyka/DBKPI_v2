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
@Table(name = "bloc")
public class Bloc {
	
	@Id
	@NotNull
	@Size(max = 6)
	@Column(name = "id_bloc")
	private String id_bloc;
	
	@NotNull
	@Column(name = "count_room")
	private int count_room;
	
	@Size(min = 20, max = 50)
	@Column(name = "bloc_vertuhay")
	private String bloc_vertuhay;
	
	@ManyToOne
	@Size(max = 4)
	@JoinColumn(name = "id_floor")
	private Floor id_floor;
	
	@OneToMany(mappedBy = "id_bloc")
	private Set<Room> rooms = new HashSet<Room>();
	
	public Bloc(){
		
	}
	
	public Bloc(String id_bloc){
		this.id_bloc = id_bloc;
	}

	public String getId_bloc() {
		return id_bloc;
	}

	public void setId_bloc(String id_bloc) {
		this.id_bloc = id_bloc;
	}

	public int getCount_room() {
		return count_room;
	}

	public void setCount_room(int count_room) {
		this.count_room = count_room;
	}

	public String getBloc_vertuhay() {
		return bloc_vertuhay;
	}

	public void setBloc_vertuhay(String bloc_vertuhay) {
		this.bloc_vertuhay = bloc_vertuhay;
	}

	public Floor getId_floor() {
		return id_floor;
	}

	public void setId_floor(Floor id_floor) {
		this.id_floor = id_floor;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	
}