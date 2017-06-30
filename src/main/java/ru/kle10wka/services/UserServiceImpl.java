package ru.kle10wka.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.UserDao;
import ru.kle10wka.table.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
	
}