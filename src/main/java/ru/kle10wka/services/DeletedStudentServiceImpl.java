package ru.kle10wka.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.DeletedStudentDao;

@Service("deletedService")
@Transactional
public class DeletedStudentServiceImpl implements DeletedStudentService{

	@Autowired
	private DeletedStudentDao dsd;
	
}