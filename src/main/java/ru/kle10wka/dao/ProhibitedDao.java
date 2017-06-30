package ru.kle10wka.dao;

import ru.kle10wka.table.Student;

public interface ProhibitedDao {
	
	public boolean isProhibited(Student st);
	
	public void updateProhibited(Student st);
}