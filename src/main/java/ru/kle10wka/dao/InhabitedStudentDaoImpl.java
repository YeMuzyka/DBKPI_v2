package ru.kle10wka.dao;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.kle10wka.table.AccessToHostel;
import ru.kle10wka.table.InhabitedStudent;
import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

@Repository
public class InhabitedStudentDaoImpl implements InhabitedStudentDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void inhabiteStudent(AccessToHostel access, Room room, Student student) {
		
		InhabitedStudent inhabitedStudent = new InhabitedStudent();
		inhabitedStudent.setInhabited_access(access);
		inhabitedStudent.setInhabited_room(room);
		inhabitedStudent.setInhabited_student(student);
		sessionFactory.getCurrentSession().save(inhabitedStudent);
	}

	@Override
	public InhabitedStudent getInhabitedStudent(Student student) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from InhabitedStudent IS where "
																	+ "IS.inhabited_student = :sid");
		query.setString("sid", student.getId_student());
		InhabitedStudent inhabitedStudent = (InhabitedStudent) query.uniqueResult();
		if(inhabitedStudent == null){
			return null;
		}
		return inhabitedStudent;
	}

	@Override
	public void deleteInhabitedStudent(InhabitedStudent inhabitedStudent) {
		
		InhabitedStudent deleteInhabitedStudent = (InhabitedStudent) sessionFactory.getCurrentSession()
												.get(InhabitedStudent.class, 
												inhabitedStudent.getId_inhabited_student());
		sessionFactory.getCurrentSession().delete(deleteInhabitedStudent);
	}

}