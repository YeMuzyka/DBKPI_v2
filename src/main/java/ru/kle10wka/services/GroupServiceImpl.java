package ru.kle10wka.services;

import java.sql.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.GroupDao;
import ru.kle10wka.table.Group;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupDao groupDao;
	
	@Override
	public List<Date> getStartSession() {
		return groupDao.getStartSession();
	}

	@Override
	public List<Date> getEndSession() {
		return groupDao.getEndSession();
	}

	@Override
	public List<String> getNameGroups() {
		return groupDao.getNameGroups();
	}

	@Override
	public List<Group> sessionIsCurrect(String id_group, Date start, Date end) {
		return groupDao.sessionIsCurrect(id_group, start, end);
	}

	@Override
	public List<Group> getAllGroup() {
		return groupDao.getAllGroup();
	}

	@Override
	public void addGroup(Group group) {
		groupDao.addGroup(group);
	}

	@Override
	public Group getGroup(String id_group) {
		return groupDao.getGroup(id_group);
	}

	@Override
	public Group updateGroup(Group update, String id_group) {
		return groupDao.updateGroup(update, id_group);
	}

}
