package ru.kle10wka.dao;

import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kle10wka.table.Group;

@Repository
public class GroupDaoImpl implements GroupDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getStartSession() {
		
		//SELECT DISTINCT start_ses FROM groups
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct G.start_ses from Group G "
				+ "order by G.start_ses");
		List<Date> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getEndSession() { 
		
		Query query = sessionFactory.getCurrentSession().createQuery("select distinct G.end_ses from Group G "
				+ "order by G.end_ses");
		List<Date> list = query.list();
		return list;
	}

	@Override
	public List<String> getNameGroups() {
		
		//SELECT id_group FROM groups WHERE id_group LIKE '%-з%'
		Query query = sessionFactory.getCurrentSession().createQuery("select G.id_group from Group G where G.id_group "
				+ " like :sf order by G.id_group");
		query.setString("sf", '%' + "-з" + '%');
		List<String> list = query.list();
		return list;
	}

	@Override
	public List<Group> sessionIsCurrect(String id_group, Date start, Date end) {
		
		//SELECT id_group, start_ses, end_ses From groups WHERE id_group = AND start_ses = AND end_ses =
		Query query = sessionFactory.getCurrentSession().createQuery("select G.id_group, G.start_ses, G.end_ses from Group G "
																	+ "where G.id_group = :id and G.start_ses = :ss and "
																	+ "G.end_ses = :es");
		query.setParameter("id", id_group);
		query.setDate("ss", start);
		query.setDate("es", end);
		List<Group> list = query.list();
		System.out.println(list);
		return list;
	}

	@Override
	public List<Group> getAllGroup() {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Group G order by G.id_group");
		List<Group> list = query.list();
		return list;
	}

	@Override
	public void addGroup(Group group) {
		
		java.sql.Date currDate = new java.sql.Date((group.getStart_ses()).getTime());
		group.setStart_ses(currDate);
		currDate = new java.sql.Date((group.getEnd_ses()).getTime());
		group.setEnd_ses(currDate);
		sessionFactory.getCurrentSession().save(group);
	}

	@Override
	public Group getGroup(String id_group) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Group G where G.id_group = :idg");
		query.setString("idg", id_group);
		Group group = (Group) query.uniqueResult();
		if(group == null){
			return null;
		}
		return group;
	}

	@Override
	public Group updateGroup(Group update, String id_group) {

		Group updateGroup = sessionFactory.getCurrentSession().get(Group.class, id_group);
		updateGroup.setId_group(id_group);
		updateGroup.setGroup_vertuhay(update.getGroup_vertuhay());
		updateGroup.setCourse(update.getCourse());
		updateGroup.setStart_ses(update.getStart_ses());
		updateGroup.setEnd_ses(update.getEnd_ses());
		sessionFactory.getCurrentSession().save(updateGroup);
		return updateGroup;
	}
	
}