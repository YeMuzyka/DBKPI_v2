package ru.kle10wka.dao;

import ru.kle10wka.table.AccessToHostel;
import ru.kle10wka.table.InhabitedStudent;
import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

public interface InhabitedStudentDao {
	
	public void inhabiteStudent(AccessToHostel access, Room room, Student student);
	
	public InhabitedStudent getInhabitedStudent(Student student);
	
	public void deleteInhabitedStudent(InhabitedStudent inhabitedStudent);
}