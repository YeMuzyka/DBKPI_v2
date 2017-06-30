package ru.kle10wka.services;

import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

public interface RoomService {
	
	public Room getFreeRoom(Student st);
	
	public void updateRoom(Room room, String male);
	
	public void incrementRoom(Room room);
}