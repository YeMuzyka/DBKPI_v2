package ru.kle10wka.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

@Repository
public class RoomDaoImpl implements RoomDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Room getFreeRoom(Student st) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Room R where R.free_in_room > 0 "
																	+ "and R.male_female = :sm_f or R.male_female = :sm_f_d");
		query.setString("sm_f", st.getMale_female());
		query.setString("sm_f_d", " ");
		List<Room> list = query.list();
		if(list.isEmpty()){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public void updateRoom(Room room, String male) {
		
		Room updateRoom = (Room) sessionFactory.getCurrentSession().get(Room.class, room.getId_room());
		int newFreeInRoom = room.getFree_in_room()-1;
		updateRoom.setFree_in_room(newFreeInRoom);
		updateRoom.setMale_female(male);
		updateRoom.setMax_in_room(room.getMax_in_room());
		updateRoom.setId_bloc(room.getId_bloc());
		sessionFactory.getCurrentSession().save(updateRoom);
	}

	@Override
	public void incrementRoom(Room room) {
		
		Room increment = (Room) sessionFactory.getCurrentSession().get(Room.class, room.getId_room());
		int newFreeInRoom = room.getFree_in_room();
		newFreeInRoom++;
		increment.setFree_in_room(newFreeInRoom);
		sessionFactory.getCurrentSession().save(increment);
	}

}