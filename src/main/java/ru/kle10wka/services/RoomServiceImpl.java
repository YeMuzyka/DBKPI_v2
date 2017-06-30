package ru.kle10wka.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.RoomDao;
import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

@Service("roomService")
@Transactional
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomDao roomDao;

	@Override
	public Room getFreeRoom(Student st) {
		return roomDao.getFreeRoom(st);
	}

	@Override
	public void updateRoom(Room room, String male) {
		roomDao.updateRoom(room, male);
	}

	@Override
	public void incrementRoom(Room room) {
		roomDao.incrementRoom(room);
	}
}