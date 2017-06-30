package ru.kle10wka.services;

import ru.kle10wka.include.InputStudent;
import ru.kle10wka.table.AccessToHostel;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.Student;

public interface AccessToHostelService {
	
	public AccessToHostel existsInDB(Student st);
	
	public AccessToHostel newAccess(Student student, InputStudent is, Hostel hostel);
	
	public AccessToHostel newAccess(Student student, Hostel hostel);
	
	public AccessToHostel updateAccess(AccessToHostel access, Student student, InputStudent is, Hostel hostel);
	
	public void updateAccess(AccessToHostel access, Student st);
	
	public void deleteAccess(AccessToHostel access);
}