package ru.kle10wka.include;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class InputStudent {
	
	@NotNull
	@Size(min = 20, max = 50)
	private String studentName;
	
	@NotNull
	@Size(min = 5, max = 7)
	private String studentGroup;
	
	@NotNull
	private Date sessionStart;
	
	@NotNull
	private Date sessionEnd;
	
	@NotNull
	private String eMail;
	
	public InputStudent(){
		
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentGroup() {
		return studentGroup;
	}

	public void setStudentGroup(String studentGroup) {
		this.studentGroup = studentGroup;
	}

	public Date getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(Date sessionStart) {
		this.sessionStart = sessionStart;
	}

	public Date getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(Date sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
}