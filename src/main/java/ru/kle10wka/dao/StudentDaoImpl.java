package ru.kle10wka.dao;

import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kle10wka.include.InputStudent;
import ru.kle10wka.include.LogConfig;
import ru.kle10wka.table.Student;

@Repository
public class StudentDaoImpl implements StudentDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	private String LOG_FILE = "C:/da/Workspace/DBKPI_v2/src/main/resources/log4j.properties";
	private Logger logger = Logger.getLogger(StudentDaoImpl.class);
	LogConfig lc = new LogConfig(LOG_FILE);
	
	@Override
	public void addStudent(Student newStudent) {
		
		java.sql.Date currDate = new java.sql.Date((newStudent.getDate_birth()).getTime());
		newStudent.setDate_birth(currDate);
		sessionFactory.getCurrentSession().save(newStudent);
	}

	@Override
	public Student getStudent(String idStudent) {
		
		Student student = (Student)sessionFactory.getCurrentSession().get(Student.class, idStudent);
		return student;
	}

	@Override
	public void removeStudent(String idStudent) {
		
	}
	
	@Override
	public Student updateStudent(Student student, String id) {
		
		Student updateStudent = (Student) sessionFactory.getCurrentSession().get(Student.class, id);
		java.sql.Date currDate = new java.sql.Date((student.getDate_birth()).getTime());
		updateStudent.setId_student(id);
		updateStudent.setFull_name(student.getFull_name());
		updateStudent.setDate_birth(currDate);
		updateStudent.setMale_female(student.getMale_female());
		updateStudent.setNeed_hurt(student.isNeed_hostel());
		updateStudent.setId_group(student.getId_group());
		sessionFactory.getCurrentSession().save(updateStudent);
		return updateStudent;
	}
	
	@Override
	public boolean currectName(String fullName) {
		
		fullName=fullName.trim();
		boolean rC1, rC2, rC3, res_Name;
		int iP;
		char ch[] = new char [1];
		//перший символ прізвища
		ch[0] = fullName.charAt(0);
		if(Character.isUpperCase(ch[0]))
		{
			rC1 = true;
		} else
			rC1 = false;
		//перший символ ім'я
		iP = fullName.indexOf(' ');
		iP++;
		ch[0] = fullName.charAt(iP);
		if(Character.isUpperCase(ch[0]))
		{
			rC2 = true;
		} else
			rC2 = false;
		//перший символ По-Батькові
		iP = fullName.lastIndexOf(' ');
		iP++;
		ch[0] = fullName.charAt(iP);
		if(Character.isUpperCase(ch[0]))
		{
			rC3 = true;
		} else
			rC3 = false;
		if(rC1 & rC2 & rC3)
		{
			res_Name = true;
		} else
			res_Name = false;
		return res_Name;
	}

	@Override
	public boolean currectEmail(String email) {
		
		boolean res_Email;
		if(email.contains("@") & email.contains(".")){
			res_Email = true;
		} else
			res_Email = false;
		return res_Email;
	}

	@Override
	public boolean validation(InputStudent student) {
		
		boolean resValidation = false;
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator val = vf.getValidator();
		student.getStudentName().trim();
		Set<ConstraintViolation<InputStudent>> resultValidation = val.validate(student);
		StringBuilder sb;
		if(resultValidation.size() != 0){
			resValidation = true;
			sb = new StringBuilder();
			for(ConstraintViolation<InputStudent> cv: resultValidation){
				sb.append("invalid field: ");
				sb.append(cv.getPropertyPath());
				sb.append("  value: ");
				sb.append(cv.getInvalidValue());
				sb.append("\n");
			}
			logger.error(sb.toString());
		} else {
			resValidation = false;
			logger.info("Дані не пусті і мають правильну довжину");
		}
		return resValidation;
	}

	@Override
	public List<Student> studentExists(InputStudent student) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Student S where S.full_name = :fn "
															+ "and S.id_group = :idg and S.need_hostel = :nf");
		query.setString("fn", student.getStudentName());
		query.setString("idg", student.getStudentGroup());
		query.setBoolean("nf", true);
		List<Student> list = query.list();
		return list;
	}

	@Override
	public List<Student> getStudentFromGroup(String id) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Student S where S.id_group = :id_g");
		query.setString("id_g", id);
		List<Student> list = query.list();
		return list;
	}
	
}