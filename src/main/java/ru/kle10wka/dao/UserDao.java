package ru.kle10wka.dao;

import ru.kle10wka.table.User;

public interface UserDao {
	
	public User findByUserName(String username);
}