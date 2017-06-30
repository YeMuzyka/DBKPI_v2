package ru.kle10wka.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "inhabited_student")
public class InhabitedStudent {
	
	@Id
	@NotNull
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id_inhabited_student")
	private int id_inhabited_student;
	
	@ManyToOne
	@JoinColumn(name = "id_student")
	private Student inhabited_student;
	
	@ManyToOne
	@JoinColumn(name = "id_access")
	private AccessToHostel inhabited_access;
	
	@ManyToOne
	@JoinColumn(name = "id_room")
	private Room inhabited_room;
	
	public InhabitedStudent(){
		
	}
	
	public InhabitedStudent(int id_inhabited_student){
		this.id_inhabited_student = id_inhabited_student;
	}

	public int getId_inhabited_student() {
		return id_inhabited_student;
	}

	public void setId_inhabited_student(int id_inhabited_student) {
		this.id_inhabited_student = id_inhabited_student;
	}

	public Student getInhabited_student() {
		return inhabited_student;
	}

	public void setInhabited_student(Student inhabited_student) {
		this.inhabited_student = inhabited_student;
	}

	public AccessToHostel getInhabited_access() {
		return inhabited_access;
	}

	public void setInhabited_access(AccessToHostel inhabited_access) {
		this.inhabited_access = inhabited_access;
	}

	public Room getInhabited_room() {
		return inhabited_room;
	}

	public void setInhabited_room(Room inhabited_room) {
		this.inhabited_room = inhabited_room;
	}
	
}