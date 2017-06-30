package ru.kle10wka.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DeletedStudentDaoImpl implements DeletedStudentDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
}