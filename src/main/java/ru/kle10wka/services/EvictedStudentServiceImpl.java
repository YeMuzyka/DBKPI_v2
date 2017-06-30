package ru.kle10wka.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.EvictedStudentDao;
import ru.kle10wka.table.EvictedStudent;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.Student;

@Service("evictedService")
@Transactional
public class EvictedStudentServiceImpl implements EvictedStudentService{

	@Autowired
	private EvictedStudentDao evictedStudentDao;
	
	@Override
	public EvictedStudent studentIsEvicted(Student st) {
		return evictedStudentDao.studentIsEvicted(st);
	}

	@Override
	public void updateEvictedStudent(EvictedStudent es, Student st) {
		evictedStudentDao.updateEvictedStudent(es, st);
	}

	@Override
	public void deleteEvictedStudent(EvictedStudent es) {
		evictedStudentDao.deleteEvictedStudent(es);
	}

	@Override
	public EvictedStudent addEvictedStudent(Student student, Hostel hostel) {
		return evictedStudentDao.addEvictedStudent(student, hostel);
	}

}
