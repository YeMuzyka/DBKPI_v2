package ru.kle10wka.table;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "groups")
public class Group {
	
	@Id
	@NotNull
	@Size(min = 5, max = 7)
	@Column(name = "id_group")
	private String id_group;
	
	@Size(min = 20, max = 50)
	@Column(name = "group_vertuhay")
	private String group_vertuhay;
	
	@Column(name = "course")
	private int course;
	
	@Column(name = "start_ses")
	private Date start_ses;
	
	@Column(name = "end_ses")
	private Date end_ses;
	
	@OneToMany(mappedBy = "id_group")
	private Set<Student> students = new HashSet<Student>();
	
	@OneToMany(mappedBy = "group")
	private Set<Prohibited> prohibiteds = new HashSet<Prohibited>();
	
	@OneToMany(mappedBy = "access_group")
	private Set<AccessToHostel> access_groups = new HashSet<AccessToHostel>();
	
	@OneToMany(mappedBy = "name_group")
	private Set<DeletedStudent> deleted_student = new HashSet<DeletedStudent>();
	
	@OneToMany(mappedBy = "group_name")
	private Set<EvictedStudent> evicted_student = new HashSet<EvictedStudent>();
	
	public Group(){
		
	}
	
	public Group(String id_group){
		this.id_group = id_group;
	}

	public String getId_group() {
		return id_group;
	}

	public void setId_group(String id_group) {
		this.id_group = id_group;
	}

	public String getGroup_vertuhay() {
		return group_vertuhay;
	}

	public void setGroup_vertuhay(String group_vertuhay) {
		this.group_vertuhay = group_vertuhay;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public Date getStart_ses() {
		return start_ses;
	}

	public void setStart_ses(Date start_ses) {
		this.start_ses = start_ses;
	}

	public Date getEnd_ses() {
		return end_ses;
	}

	public void setEnd_ses(Date end_ses) {
		this.end_ses = end_ses;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Prohibited> getProhibiteds() {
		return prohibiteds;
	}

	public void setProhibiteds(Set<Prohibited> prohibiteds) {
		this.prohibiteds = prohibiteds;
	}

	public Set<AccessToHostel> getAccess_groups() {
		return access_groups;
	}

	public void setAccess_groups(Set<AccessToHostel> access_groups) {
		this.access_groups = access_groups;
	}

	public Set<DeletedStudent> getDeleted_student() {
		return deleted_student;
	}

	public void setDeleted_student(Set<DeletedStudent> deleted_student) {
		this.deleted_student = deleted_student;
	}

	/*@Override
	public String toString() {
		return "[ " + id_group + ", " + group_vertuhay + ", " + course + ", "
				+ start_ses + ", " + end_ses + " ]";
	}*/
	
	@Override
	public String toString() {
		return id_group;
	}
	
}