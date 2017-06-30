package ru.kle10wka.table;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "access")
public class AccessToHostel {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id_access")
	private int id_access;
	
	@ManyToOne
	@JoinColumn(name = "id_student")
	private Student access_student;
	
	@ManyToOne
	@JoinColumn(name = "id_group")
	private Group access_group;
	
	@ManyToOne
	@JoinColumn(name = "id_hostel")
	private Hostel access_hostel;
	
	@Column(name = "access_start")
	private Date access_start;
	
	@Column(name = "access_end")
	private Date access_end;
	
	@OneToMany(mappedBy = "inhabited_access")
	private Set<InhabitedStudent> inhabited_accesses = new HashSet<InhabitedStudent>();
	
	public AccessToHostel(){
		
	}
	
	public AccessToHostel(int id_access){
		this.id_access = id_access;
	}

	public int getId_access() {
		return id_access;
	}

	public void setId_access(int id_access) {
		this.id_access = id_access;
	}

	public Student getAccess_student() {
		return access_student;
	}

	public void setAccess_student(Student access_student) {
		this.access_student = access_student;
	}

	public Group getAccess_group() {
		return access_group;
	}

	public void setAccess_group(Group access_group) {
		this.access_group = access_group;
	}

	public Hostel getAccess_hostel() {
		return access_hostel;
	}

	public void setAccess_hostel(Hostel access_hostel) {
		this.access_hostel = access_hostel;
	}

	public Date getAccess_start() {
		return access_start;
	}

	public void setAccess_start(Date access_start) {
		this.access_start = access_start;
	}

	public Date getAccess_end() {
		return access_end;
	}

	public void setAccess_end(Date access_end) {
		this.access_end = access_end;
	}

	public Set<InhabitedStudent> getInhabited_accesses() {
		return inhabited_accesses;
	}

	public void setInhabited_accesses(Set<InhabitedStudent> inhabited_accesses) {
		this.inhabited_accesses = inhabited_accesses;
	}
	
}