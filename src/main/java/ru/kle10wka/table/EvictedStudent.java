package ru.kle10wka.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "evicted_student")
public class EvictedStudent {

	@Id
	@NotNull
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id_evicted")
	private int id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "name_student")
	private Student name_student;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "name_group")
	private Group group_name;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "name_hostel")
	private Hostel hostel;
	
	@NotNull
	@Column(name = "reason")
	@Size(min = 10, max = 50)
	private String reason;

	public EvictedStudent() {
		
	}

	public EvictedStudent(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getName_student() {
		return name_student;
	}

	public void setName_student(Student name_student) {
		this.name_student = name_student;
	}

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Group getGroup_name() {
		return group_name;
	}

	public void setGroup_name(Group group_name) {
		this.group_name = group_name;
	}
	
}