package ru.kle10wka.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.kle10wka.table.EvictedStudent;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.Student;

@Repository
public class EvictedStudentDaoImpl implements EvictedStudentDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public EvictedStudent studentIsEvicted(Student st) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from EvictedStudent ES where ES.name_student = :ns");
		query.setString("ns", st.getId_student());
		List<EvictedStudent> list = query.list();
		if(list.isEmpty()){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public void updateEvictedStudent(EvictedStudent es, Student st) {
		
		EvictedStudent updateEvictedStudent = (EvictedStudent) sessionFactory.getCurrentSession()
																					.get(EvictedStudent.class, es.getId());
		updateEvictedStudent.setGroup_name(st.getId_group());
		sessionFactory.getCurrentSession().save(updateEvictedStudent);
	}

	@Override
	public void deleteEvictedStudent(EvictedStudent es) {
		
		EvictedStudent deleteEvictedStudent = (EvictedStudent) sessionFactory.getCurrentSession()
																					.get(EvictedStudent.class, es.getId());
		sessionFactory.getCurrentSession().delete(deleteEvictedStudent);
	}

	@Override
	public EvictedStudent addEvictedStudent(Student student, Hostel hostel) {
		
		EvictedStudent evictedStudent = new EvictedStudent();
		evictedStudent.setReason("Більше не потребує гуртожитка");
		evictedStudent.setGroup_name(student.getId_group());
		evictedStudent.setHostel(hostel);
		evictedStudent.setName_student(student);
		sessionFactory.getCurrentSession().save(evictedStudent);
		return evictedStudent;
	}
	
}