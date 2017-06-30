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
@Table(name = "deleted_student")
public class DeletedStudent {

	@Id
	@NotNull
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id_del_stud")
	private int id;
	
	@NotNull
	@Column(name = "full_name")
	@Size(min = 20, max = 50)
	private String fullName;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "name_group")
	private Group name_group;
	
	@NotNull
	@Column(name = "reason")
	@Size(min = 10, max = 50)
	private String reason;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Group getName_group() {
		return name_group;
	}

	public void setName_group(Group name_group) {
		this.name_group = name_group;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public DeletedStudent() {
		
	}

	public DeletedStudent(int id) {
		this.id = id;
	}
	
}