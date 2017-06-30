package ru.kle10wka.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import ru.kle10wka.table.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public User findByUserName(String username) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from User U where U.login = :login");
		query.setString("login", username);
		List<User> user = query.list();
		if(user.size() > 0){
			return user.get(0);
		} else{
			return null;
		}
	}
	
}