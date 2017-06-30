package ru.kle10wka.services;

import ru.kle10wka.table.User;

public interface UserService {

	public User findByUserName(String username);
}