package ru.kle10wka.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.kle10wka.include.InputStudent;
import ru.kle10wka.table.AccessToHostel;
import ru.kle10wka.table.Group;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.Student;

@Repository
public class AccessToHostelDaoImpl implements AccessToHostelDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public AccessToHostel existsInDB(Student st) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from AccessToHostel A where "
																	+ "A.access_student = :ids");
		query.setString("ids", st.getFull_name());
		List<AccessToHostel> list = query.list();
		if(list.isEmpty()){
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public AccessToHostel newAccess(Student student, InputStudent is, Hostel hostel) {
		
		AccessToHostel newAccess = new AccessToHostel();
		newAccess.setAccess_end(is.getSessionEnd());
		newAccess.setAccess_start(is.getSessionStart());
		newAccess.setAccess_group(student.getId_group());
		newAccess.setAccess_hostel(hostel);
		newAccess.setAccess_student(student);
		sessionFactory.getCurrentSession().save(newAccess);
		return newAccess;
	}

	@Override
	public AccessToHostel updateAccess(AccessToHostel access, Student student, InputStudent is, Hostel hostel) {
		
		AccessToHostel updateAccess = (AccessToHostel) sessionFactory.getCurrentSession()
				.get(AccessToHostel.class, access.getId_access());
		updateAccess.setAccess_end(is.getSessionEnd());
		updateAccess.setAccess_start(is.getSessionStart());
		updateAccess.setAccess_group(student.getId_group());
		updateAccess.setAccess_hostel(hostel);
		updateAccess.setAccess_student(student);
		sessionFactory.getCurrentSession().save(updateAccess);
		return updateAccess;
	}

	@Override
	public void updateAccess(AccessToHostel access, Student st) {
		
		Group group = (Group) sessionFactory.getCurrentSession().get(Group.class, st.getId_group().getId_group());
		AccessToHostel updateAccess = (AccessToHostel) sessionFactory.getCurrentSession()
																.get(AccessToHostel.class, access.getId_access());
		updateAccess.setAccess_group(group);
		updateAccess.setAccess_start(group.getStart_ses());
		updateAccess.setAccess_end(group.getEnd_ses());
		updateAccess.setAccess_student(st);
		sessionFactory.getCurrentSession().save(updateAccess);
	}

	@Override
	public AccessToHostel newAccess(Student student, Hostel hostel) {
		
		AccessToHostel newAccess = new AccessToHostel();
		newAccess.setAccess_end(student.getId_group().getEnd_ses());
		newAccess.setAccess_start(student.getId_group().getStart_ses());
		newAccess.setAccess_group(student.getId_group());
		newAccess.setAccess_hostel(hostel);
		newAccess.setAccess_student(student);
		sessionFactory.getCurrentSession().save(newAccess);
		return newAccess;
	}

	@Override
	public void deleteAccess(AccessToHostel access) {
		
		AccessToHostel deleteAccess = (AccessToHostel) sessionFactory.getCurrentSession()
															.get(AccessToHostel.class, access.getId_access());
		sessionFactory.getCurrentSession().delete(deleteAccess);
	}

}