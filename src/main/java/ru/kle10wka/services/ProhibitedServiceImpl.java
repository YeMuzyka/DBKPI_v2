package ru.kle10wka.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.ProhibitedDao;
import ru.kle10wka.table.Student;

@Service("prohibitedService")
@Transactional
public class ProhibitedServiceImpl implements ProhibitedService{
	
	@Autowired
	private ProhibitedDao prohibitedDao;

	@Override
	public boolean isProhibited(Student st) {
		return prohibitedDao.isProhibited(st);
	}

	@Override
	public void updateProhibited(Student st) {
		prohibitedDao.updateProhibited(st);
	}
	
}