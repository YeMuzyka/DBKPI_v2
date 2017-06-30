package ru.kle10wka.services;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.HostelDao;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

@Service("hostelService")
@Transactional
public class HostelServiceImpl implements HostelService{

	@Autowired
	private HostelDao hostelDao;
	
	@Override
	public Hostel getIdHostelFromRoom(Room room) {
		return hostelDao.getIdHostelFromRoom(room);
	}

	@Override
	public void writeToFile(Student student, Room room, Hostel hostel) {
		hostelDao.writeToFile(student, room, hostel);
	}

	@Override
	public String readFile(int num) {
		return hostelDao.readFile(num);
	}

	@Override
	public Hostel getHostelById(Integer id) {
		return hostelDao.getHostelById(id);
	}

	@Override
	public List<Integer> getAllIdHostel() {
		return hostelDao.getAllIdHostel();
	}

}