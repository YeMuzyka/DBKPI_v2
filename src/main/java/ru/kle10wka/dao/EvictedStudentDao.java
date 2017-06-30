package ru.kle10wka.dao;

import ru.kle10wka.table.EvictedStudent;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.Student;

public interface EvictedStudentDao {

	public EvictedStudent studentIsEvicted(Student st);
	
	public void updateEvictedStudent(EvictedStudent es, Student st);
	
	public void deleteEvictedStudent(EvictedStudent es);
	
	public EvictedStudent addEvictedStudent(Student student, Hostel hostel);
	
}