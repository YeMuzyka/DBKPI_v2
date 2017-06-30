package ru.kle10wka.dao;

import java.sql.Date;
import java.util.List;

import ru.kle10wka.table.Group;

public interface GroupDao {
	
	public List<Date> getStartSession();
	
	public List<Date> getEndSession();
	
	public List<String> getNameGroups();
	
	public List<Group> sessionIsCurrect(String id_group, Date start, Date end);
	
	public List<Group> getAllGroup();
	
	public void addGroup(Group group);
	
	public Group getGroup(String id_group);
	
	public Group updateGroup(Group update, String id_group);
}