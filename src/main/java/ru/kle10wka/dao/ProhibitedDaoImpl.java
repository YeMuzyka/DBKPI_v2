package ru.kle10wka.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.kle10wka.table.Prohibited;
import ru.kle10wka.table.Student;

@Repository
public class ProhibitedDaoImpl implements ProhibitedDao{
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public boolean isProhibited(Student st) {
		
		boolean isProhibited;
		Query query = sessionFactory.getCurrentSession().createQuery("from Prohibited P where "
																	+ "P.student = :id");
		query.setString("id", st.getId_student());
		List<Prohibited> list = query.list();
		if(list.isEmpty()){
			isProhibited = false;
		} else
			isProhibited = true;
		return isProhibited;
	}

	@Override
	public void updateProhibited(Student st) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Prohibited P where "
																	+ "P.student = :id");
		query.setString("id", st.getId_student());
		Prohibited list = (Prohibited) query.uniqueResult();
		list.setGroup(st.getId_group());
		sessionFactory.getCurrentSession().save(list);
	}
	
}