package ru.kle10wka.table;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "hostel")
public class Hostel {
	
	@Id
	@NotNull
	@Max(20)
	@Column(name = "id_hostel")
	private int id_hostel;
	
	@Size(min = 20, max = 50)
	@Column(name = "hostel_vertuhay")
	private String hostel_vertuhay;
	
	@Size(max = 30)
	@Column(name = "address")
	private String address;
	
	@Column(name = "count_free_room")
	private int count_free_room;
	
	@OneToMany(mappedBy = "id_hostel")
	private Set<Floor> floors = new HashSet<Floor>();
	
	@OneToMany(mappedBy = "access_hostel")
	private Set<AccessToHostel> access_hostels = new HashSet<AccessToHostel>();
	
	@OneToMany(mappedBy = "hostel")
	private Set<EvictedStudent> evicted_hostels = new HashSet<EvictedStudent>();
	
	public Hostel(){
		
	}
	
	public Hostel(int id_hostel){
		this.id_hostel = id_hostel;
	}

	public int getId_hostel() {
		return id_hostel;
	}

	public void setId_hostel(int id_hostel) {
		this.id_hostel = id_hostel;
	}

	public String getHostel_vertuhay() {
		return hostel_vertuhay;
	}

	public void setHostel_vertuhay(String hostel_vertuhay) {
		this.hostel_vertuhay = hostel_vertuhay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCount_free_room() {
		return count_free_room;
	}

	public void setCount_free_room(int count_free_room) {
		this.count_free_room = count_free_room;
	}

	public Set<Floor> getFloors() {
		return floors;
	}

	public void setFloors(Set<Floor> floors) {
		this.floors = floors;
	}

	public Set<AccessToHostel> getAccess_hostels() {
		return access_hostels;
	}

	public void setAccess_hostels(Set<AccessToHostel> access_hostels) {
		this.access_hostels = access_hostels;
	}

	public Set<EvictedStudent> getEvicted_hostels() {
		return evicted_hostels;
	}

	public void setEvicted_hostels(Set<EvictedStudent> evicted_hostels) {
		this.evicted_hostels = evicted_hostels;
	}
	
}