package ru.kle10wka.dao;

import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

public interface RoomDao {
	
	public Room getFreeRoom(Student st);
	
	public void updateRoom(Room room, String male);
	
	public void incrementRoom(Room room);
}