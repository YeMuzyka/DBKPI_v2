package ru.kle10wka.services;

import ru.kle10wka.table.Student;

public interface ProhibitedService {
	
	public boolean isProhibited(Student st);
	
	public void updateProhibited(Student st);
}