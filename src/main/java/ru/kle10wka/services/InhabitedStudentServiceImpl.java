package ru.kle10wka.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.InhabitedStudentDao;
import ru.kle10wka.table.AccessToHostel;
import ru.kle10wka.table.InhabitedStudent;
import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

@Service("inhabitedService")
@Transactional
public class InhabitedStudentServiceImpl implements InhabitedStudentService{
	
	@Autowired
	private InhabitedStudentDao iSD;

	@Override
	public void inhabiteStudent(AccessToHostel access, Room room, Student student) {
		iSD.inhabiteStudent(access, room, student);
	}

	@Override
	public InhabitedStudent getInhabitedStudent(Student student) {
		return iSD.getInhabitedStudent(student);
	}

	@Override
	public void deleteInhabitedStudent(InhabitedStudent inhabitedStudent) {
		iSD.deleteInhabitedStudent(inhabitedStudent);
	}

}