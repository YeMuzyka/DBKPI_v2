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
@Table(name = "prohibited")
public class Prohibited {
	
	@Id
	@NotNull
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id_prohibited")
	private int id_prohibited;
	
	@ManyToOne
	@Size(max = 10)
	@JoinColumn(name = "id_student")
	private Student student;
	
	@ManyToOne
	@Size(min = 5, max = 7)
	@JoinColumn(name = "id_group")
	private Group group;
	
	
	public Prohibited(){
		
	}
	
	public Prohibited(int id_prohibited){
		this.id_prohibited = id_prohibited;
	}

	public int getId_prohibited() {
		return id_prohibited;
	}

	public void setId_prohibited(int id_prohibited) {
		this.id_prohibited = id_prohibited;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}