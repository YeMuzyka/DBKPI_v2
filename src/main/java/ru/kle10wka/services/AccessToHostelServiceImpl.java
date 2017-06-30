package ru.kle10wka.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.AccessToHostelDao;
import ru.kle10wka.include.InputStudent;
import ru.kle10wka.table.AccessToHostel;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.Student;

@Service("accessService")
@Transactional
public class AccessToHostelServiceImpl implements AccessToHostelService{

	@Autowired
	private AccessToHostelDao accessDao;
	
	@Override
	public AccessToHostel existsInDB(Student st) {
		return accessDao.existsInDB(st);
	}

	@Override
	public AccessToHostel newAccess(Student student, InputStudent is, Hostel hostel) {
		return accessDao.newAccess(student, is, hostel);
	}

	@Override
	public AccessToHostel updateAccess(AccessToHostel access, Student student, InputStudent is, Hostel hostel) {
		return accessDao.updateAccess(access, student, is, hostel);
	}

	@Override
	public void updateAccess(AccessToHostel access, Student st) {
		accessDao.updateAccess(access, st);
	}

	@Override
	public AccessToHostel newAccess(Student student, Hostel hostel) {
		return accessDao.newAccess(student, hostel);
	}

	@Override
	public void deleteAccess(AccessToHostel access) {
		accessDao.deleteAccess(access);
	}

}
