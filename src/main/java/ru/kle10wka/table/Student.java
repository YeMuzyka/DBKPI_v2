package ru.kle10wka.table;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@NotNull
	@Size(max = 10)
	@Column(name = "id_student")
	private String id_student;
	
	@NotNull
	@Size(min = 20, max = 50)
	@Column(name = "full_name")
	private String full_name;
	
	@NotNull
	@Past
	@Column(name = "date_birth")
	private Date date_birth;
	
	@NotNull
	@Size(max = 1)
	@Column(name = "male_female")
	private String male_female;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "name_group")
	private Group id_group;
	
	@Column(name = "need_hostel")
	private boolean need_hostel;
	
	@OneToMany(mappedBy = "student")
	private Set<Prohibited> prohibiteds = new HashSet<Prohibited>();
	
	@OneToMany(mappedBy = "access_student")
	private Set<AccessToHostel> access_students = new HashSet<AccessToHostel>();
	
	@OneToMany(mappedBy = "inhabited_student")
	private Set<InhabitedStudent> inhabited_students = new HashSet<InhabitedStudent>();
	
	
	@OneToMany(mappedBy = "name_student")
	private Set<EvictedStudent> evicted_students = new HashSet<EvictedStudent>();
	public Student() {
		
	}
	
	public Student(String id_student){
		this.id_student = id_student;
	}

	public String getId_student() {
		return id_student;
	}

	public void setId_student(String id_student) {
		this.id_student = id_student;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public Date getDate_birth() {
		return date_birth;
	}

	public void setDate_birth(Date date_birth) {
		this.date_birth = date_birth;
	}

	public String getMale_female() {
		return male_female;
	}

	public void setMale_female(String male_female) {
		this.male_female = male_female;
	}

	public Group getId_group() {
		return id_group;
	}

	public void setId_group(Group id_group) {
		this.id_group = id_group;
	}

	public boolean isNeed_hostel() {
		return need_hostel;
	}

	public void setNeed_hurt(boolean need_hostel) {
		this.need_hostel = need_hostel;
	}

	public Set<Prohibited> getProhibiteds() {
		return prohibiteds;
	}

	public void setProhibiteds(Set<Prohibited> prohibiteds) {
		this.prohibiteds = prohibiteds;
	}

	public Set<AccessToHostel> getAccess_students() {
		return access_students;
	}

	public void setAccess_students(Set<AccessToHostel> access_students) {
		this.access_students = access_students;
	}

	public Set<InhabitedStudent> getInhabited_students() {
		return inhabited_students;
	}

	public void setInhabited_students(Set<InhabitedStudent> inhabited_students) {
		this.inhabited_students = inhabited_students;
	}

	public Set<EvictedStudent> getEvicted_students() {
		return evicted_students;
	}

	public void setEvicted_students(Set<EvictedStudent> evicted_students) {
		this.evicted_students = evicted_students;
	}

	@Override
	public String toString() {
		return "[ " + id_student + ", " + full_name + ", " + date_birth + ", " 
				+ male_female + ", " + need_hostel + ", " + id_group + " ]";
	}
	
}