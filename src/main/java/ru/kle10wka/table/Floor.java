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
@Table(name = "floor")
public class Floor {
	
	@Id
	@NotNull
	@Size(max = 4)
	@Column(name = "id_floor")
	private String id_floor;
	
	@Column(name = "count_bloc")
	private int count_bloc;
	
	@Size(min = 20, max = 50)
	@Column(name = "floor_vertuhay")
	private String floor_vertuhay;
	
	@ManyToOne
	@JoinColumn(name = "hostel")
	private Hostel id_hostel;
	
	@OneToMany(mappedBy = "id_floor")
	private Set<Bloc> blocs = new HashSet<Bloc>();
	
	public Floor(){
		
	}
	
	public Floor(String id_floor){
		this.id_floor = id_floor;
	}

	public String getId_floor() {
		return id_floor;
	}

	public void setId_floor(String id_floor) {
		this.id_floor = id_floor;
	}

	public int getCount_bloc() {
		return count_bloc;
	}

	public void setCount_bloc(int count_bloc) {
		this.count_bloc = count_bloc;
	}

	public String getFloor_vertuhay() {
		return floor_vertuhay;
	}

	public void setFloor_vertuhay(String floor_vertuhay) {
		this.floor_vertuhay = floor_vertuhay;
	}

	public Hostel getId_hostel() {
		return id_hostel;
	}

	public void setId_hostel(Hostel id_hostel) {
		this.id_hostel = id_hostel;
	}

	public Set<Bloc> getBlocs() {
		return blocs;
	}

	public void setBlocs(Set<Bloc> blocs) {
		this.blocs = blocs;
	}
	
}