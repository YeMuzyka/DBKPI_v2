package ru.kle10wka.services;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kle10wka.dao.StudentDao;
import ru.kle10wka.include.InputStudent;
import ru.kle10wka.table.Student;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public void addStudent(Student newStudent) {
		studentDao.addStudent(newStudent);
	}

	@Override
	public Student getStudent(String idStudent) {
		return studentDao.getStudent(idStudent);
	}

	@Override
	public void removeStudent(String idStudent) {
		studentDao.removeStudent(idStudent);
	}

	@Override
	public Student updateStudent(Student student, String id) {
		return studentDao.updateStudent(student, id);
	}

	@Override
	public boolean currectName(String fullName) {
		return studentDao.currectName(fullName);
	}

	@Override
	public boolean currectEmail(String email) {
		return studentDao.currectEmail(email);
	}

	@Override
	public boolean validation(InputStudent student) {
		return studentDao.validation(student);
	}

	@Override
	public List<Student> studentExists(InputStudent student) {
		return studentDao.studentExists(student);
	}

	@Override
	public List<Student> getStudentFromGroup(String id) {
		return studentDao.getStudentFromGroup(id);
	}

}