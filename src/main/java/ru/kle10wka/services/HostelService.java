package ru.kle10wka.services;

import java.util.List;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

public interface HostelService {
	
	public Hostel getIdHostelFromRoom(Room room);
	
	public void writeToFile(Student student, Room room, Hostel hostel);
	
	public String readFile(int num);
	
	public Hostel getHostelById(Integer id);
	
	public List<Integer> getAllIdHostel();
}