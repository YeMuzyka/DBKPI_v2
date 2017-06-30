package ru.kle10wka.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

@Repository
public class HostelDaoImpl implements HostelDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Hostel getIdHostelFromRoom(Room room) {
		Hostel numberHostel = new Hostel();
		char ch[] = new char[1];
		ch[0] = room.getId_room().charAt(1);
		if(ch[0] == '9'){
			numberHostel = (Hostel) sessionFactory.getCurrentSession().get(Hostel.class, 19);
		} else 
			numberHostel = (Hostel) sessionFactory.getCurrentSession().get(Hostel.class, 18);
		return numberHostel;
	}

	@Override
	public void writeToFile(Student student, Room room, Hostel hostel) {
		String str = new String();
		str = hostel.getId_hostel() + "-> " + student.getFull_name() + "\t" + room.getId_room() + 
				"\t" + student.getId_group().getStart_ses() + "\t" + student.getId_group().getEnd_ses()
				+ "\n";
		String file_Path = "C://da/Workspace/DBKPI_v2/temp_Files/" + hostel.getId_hostel() + ".txt";
		File file = new File(file_Path);
		try{
			if(!file.exists()){
	            file.createNewFile();
	        }
	        try{
	        	FileWriter writer = new FileWriter(file_Path, true);
		        BufferedWriter bufferWriter = new BufferedWriter(writer);
		        bufferWriter.write(str);
		        bufferWriter.close();
		    } catch (IOException e){
		    	System.out.println("Error: " + e);
		    }
	    } catch(IOException e){
	        System.out.println("Error: " + e);
	    }
	}

	@Override
	public String readFile(int num) {
		StringBuilder sb = new StringBuilder();
		String file_Path = "C://da/Workspace/DBKPI_v2/temp_Files/" + num + ".txt";
		File file = new File(file_Path);
		if(!file.exists()) {
			String not_Exists = "Нових заселених студентів немає!";
			sb.append(not_Exists);
			sb.append("\n");
		} else {
    		try(BufferedReader read = new BufferedReader(new FileReader (file_Path))) {
    			String tmp_Str;
    			while ((tmp_Str = read.readLine()) != null) {
    				sb.append(tmp_Str);
    				sb.append("\n");
    			}
    			read.close();
    			if(file.delete()) {
    				System.out.println("Deleting file successful");
    			} else System.out.println("Deleting file ERROR!");
    		} catch(FileNotFoundException fnfe) {
    			System.out.println("Error: " + fnfe);
			} catch (IOException ioe) {
				System.out.println("Error: " + ioe);
			}
		}
		return sb.toString();
	}

	@Override
	public Hostel getHostelById(Integer id) {
		
		Hostel hostel = (Hostel) sessionFactory.getCurrentSession().get(Hostel.class, id);
		return hostel;
	}

	@Override
	public List<Integer> getAllIdHostel() {
		
		Query query = sessionFactory.getCurrentSession().createQuery("select H.id_hostel from Hostel H");
		List<Integer> allId = query.list();
		return allId;
	}

}