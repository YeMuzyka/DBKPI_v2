package ru.kle10wka.services;

public interface SecurityService {
	
	public String findloggedUsername();
	
	public void autologin(String username, String password);
}