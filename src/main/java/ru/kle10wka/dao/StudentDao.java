package ru.kle10wka.dao;

import java.util.List;

import ru.kle10wka.include.InputStudent;
import ru.kle10wka.table.Student;

public interface StudentDao {
	
	public void addStudent(Student newStudent);
	
	public Student getStudent(String idStudent);
	
	public void removeStudent(String idStudent);
	
	public Student updateStudent(Student student, String id);
	
	public boolean currectName(String fullName);
	
	public boolean currectEmail(String email);
	
	public boolean validation(InputStudent student);
	
	public List<Student> studentExists(InputStudent student);
	
	public List<Student> getStudentFromGroup(String id);
	
}