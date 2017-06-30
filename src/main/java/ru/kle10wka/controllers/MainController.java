package ru.kle10wka.controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kle10wka.include.EMailInit;
import ru.kle10wka.include.InputStudent;
import ru.kle10wka.include.LogConfig;
import ru.kle10wka.services.AccessToHostelService;
import ru.kle10wka.services.DeletedStudentService;
import ru.kle10wka.services.EvictedStudentService;
import ru.kle10wka.services.GroupService;
import ru.kle10wka.services.HostelService;
import ru.kle10wka.services.InhabitedStudentService;
import ru.kle10wka.services.ProhibitedService;
import ru.kle10wka.services.RoomService;
import ru.kle10wka.services.StudentService;
import ru.kle10wka.table.AccessToHostel;
import ru.kle10wka.table.EvictedStudent;
import ru.kle10wka.table.Group;
import ru.kle10wka.table.Hostel;
import ru.kle10wka.table.InhabitedStudent;
import ru.kle10wka.table.Room;
import ru.kle10wka.table.Student;

@Controller
public class MainController {
	
	@Resource(name = "groupService")
	private GroupService gs;
	
	@Resource(name = "studentService")
	private StudentService ss;
	
	@Resource(name = "prohibitedService")
	private ProhibitedService ps;
	
	@Resource(name = "roomService")
	private RoomService rs;
	
	@Resource(name = "accessService")
	private AccessToHostelService access;
	
	@Resource(name = "inhabitedService")
	private InhabitedStudentService inhabited;
	
	@Resource(name = "hostelService")
	private HostelService hs;
	
	@Resource(name = "evictedService")
	private EvictedStudentService ess;
	
	@Resource(name = "deletedService")
	private DeletedStudentService dss;
	
	private String LOG_FILE = "C:/da/Workspace/DBKPI_v2/src/main/resources/log4j.properties";
	private Logger logger = Logger.getLogger(MainController.class);
	LogConfig lc = new LogConfig(LOG_FILE);
	EMailInit email = new EMailInit();
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView search(Model mod) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<Date> list_start = gs.getStartSession();
		mod.addAttribute("session_start", list_start);
		
		List<Date> list_end = gs.getEndSession();
		mod.addAttribute("session_end", list_end);
		
		List<String> list_name = gs.getNameGroups();
		mod.addAttribute("name_group", list_name);
		
		modelAndView.addObject("inputStudent", new InputStudent());
		modelAndView.setViewName("search");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/result-search")
	public ModelAndView resultSearch(@ModelAttribute("inputStudent") InputStudent student){
		
		String result;
		ModelAndView modelAndView = new ModelAndView();
		logger.info("Почато перевірку даних з форми");
		boolean resValidation = ss.validation(student);
		if(resValidation){
			result = "Некоректні введені дані";
			logger.info(result);
			modelAndView.addObject("message", result);
			modelAndView.setViewName("result-search");
		} else {
			logger.info("Перевірка даних на перші літери та коректність e-mail");
			boolean res_Name = ss.currectName(student.getStudentName());
			boolean res_Email = ss.currectEmail(student.geteMail());
			if(!res_Name | !res_Email){
				String errorString = "Некоректні ім'я або e-mail. Поверніться назад та введіть правильні дані";
				logger.info(errorString);
				modelAndView.addObject("message", errorString);
				modelAndView.setViewName("result-search");
			} else{
				String successfulString = "Коректні ім'я та email";
				logger.info(successfulString);
				List<Student> exists = ss.studentExists(student);
				if(exists.isEmpty()){
					result = "Студента не існує";
					logger.info(result);
					modelAndView.addObject("message", result);
					modelAndView.setViewName("result-search");
				} else {
					result = "Студент існує";
					logger.info(result);
					Student st = exists.get(0);
					result = "Звіряємо сесію";
					logger.info(result);
					List<Group> sessionResult = gs.sessionIsCurrect(student.getStudentGroup(), student.getSessionStart(), 
																student.getSessionEnd());
					if(sessionResult.isEmpty()){
						result = "Сесії не співпадають";
						logger.info(result);
						modelAndView.addObject("message", result);
						modelAndView.setViewName("result-search");
					} else{
						result = "Сесії співпадають";
						logger.info(result);
						result = "Студент забороненний ?";
						logger.info(result);
						boolean isProh = ps.isProhibited(st);
						if(isProh){
							result = "Студент заборонений до поселення";
							logger.info(result);
							modelAndView.addObject("message", result);
							modelAndView.setViewName("result-search");
						} else{
							result = "Студент не забороненний до поселення";
							logger.info(result);
							result = "Пошук вільного місця..........";
							logger.info(result);
							Room freeRoom = rs.getFreeRoom(st);
							if(freeRoom == null){
								result = "Немає вільного місця для данної статі";
								logger.info(result);
								modelAndView.addObject("message", result);
								modelAndView.setViewName("result-search");
							}else{
								result = "Для данної статі є вільне місце";
								Hostel hostel = hs.getIdHostelFromRoom(freeRoom);
								logger.info(result);
								String emailText = "Шановний(на) " + st.getFull_name() + ", для заселення в гуртожиток на час сесії, "
							            			+ "Вам необхідно оплатити проживання. Реквізити: " + "\n" + "Постачальник "
							            			+ "послуг: НТУУ\"КПІ\"" + "\n" + "Р/Р: 31254289213853 МФО 820172 "
							            			+ "ГУДКСУ м.Києва ЕДРППОУ 02070921" + "\n"
							            			+ "Призначення платежу: проживання заочника." + "\n"
							            			+ "Ціна за одну ніч складає 70 грн.";
								try{
									email.sendMessage(student.geteMail(), emailText);
									logger.info("Лист успішно надісланий");
								}catch (MessagingException me) {
									logger.error("Листа відправити неможливо: " + me);
								}
								result = "Студент є у базі перепусток?";
								logger.info(result);
								AccessToHostel accessToHostel = access.existsInDB(st);
								if(accessToHostel == null){
									result = "Студента у базі перепусток немає";
									logger.info(result);
									logger.info("Номер гуртожитку: " + hostel.getId_hostel());
									accessToHostel = access.newAccess(st, student, hostel);
									logger.info("№ нового пропуск: " + accessToHostel.getId_access());
									inhabited.inhabiteStudent(accessToHostel, freeRoom, st);
									logger.info("Студента заселено у кімнату: " + freeRoom.getId_room());
									logger.info("Відправка листа");
									emailText = "Вас заселенно в гуртожиток №: " + hostel.getId_hostel() + " в кімнату: " 
														+ freeRoom.getId_room() + "." + "\nАдреса гуртожитку: " 
														+ hostel.getAddress() + "." + "\nЯкщо у Вас немає перепустки, "
														+ "зверніться до Бюро Перепусток за адресою: "
					    		            			+ "Гуртожиток № 7 вул. Металістів, 3";
									try{
										email.sendMessage(student.geteMail(), emailText);
										logger.info("Лист успішно надісланий");
									}catch (MessagingException me) {
										logger.error("Листа відправити неможливо: " + me);
									}
									logger.info("Запис до файлу інформації про заселеного студента до гуртожитку");
									hs.writeToFile(st, freeRoom, hostel);
									logger.info("Оновлення даних для кімнати: " + freeRoom.getId_room()
												+ "Вільного місця: " + freeRoom.getFree_in_room());
									rs.updateRoom(freeRoom, st.getMale_female());
									logger.info("Кімнату оновлено: " + freeRoom.getId_room()
												+ " Вільного місця: " + freeRoom.getFree_in_room());
									result = "Вас заселенно в гуртожиток №: " + hostel.getId_hostel() + " в кімнату: " 
														+ freeRoom.getId_room() + ". Детальніше про оплату в листі, "
														+ "який надісланий на: " + student.geteMail();
									logger.info(result);
									modelAndView.addObject("message", result);
									modelAndView.setViewName("result-search");
								}else{
									result= "Студент є у базі перепусток";
									logger.info(result);
									logger.info("Номер гуртожитку: " + hostel.getId_hostel());
									result = "Старі дані пропуску: " + accessToHostel.getId_access() + ", " 
											  + accessToHostel.getAccess_end() + ", " + accessToHostel.getAccess_start()
											  + ", " + accessToHostel.getAccess_group() + ", "
											  + accessToHostel.getAccess_hostel() + ", " 
											  + accessToHostel.getAccess_student();
									logger.info(result);
									AccessToHostel updateAccess = access.updateAccess(accessToHostel, st, student, hostel);
									result = "Оновлені дані пропуску: " + updateAccess.getId_access() + ", " 
											  + updateAccess.getAccess_end() + ", " + updateAccess.getAccess_start()
											  + ", " + updateAccess.getAccess_group() + ", "
											  + updateAccess.getAccess_hostel() + ", " 
											  + updateAccess.getAccess_student();
									logger.info(result);
									inhabited.inhabiteStudent(updateAccess, freeRoom, st);
									logger.info("Студента заселено у кімнату: " + freeRoom.getId_room());
									logger.info("Відправка листа");
									emailText = "Вас заселенно в гуртожиток №: " + hostel.getId_hostel() + " в кімнату: " 
											+ freeRoom.getId_room() + "." + "\nАдреса гуртожитку: " 
											+ hostel.getAddress() + "." + "\nЯкщо у Вас немає перепустки, "
											+ "зверніться до Бюро Перепусток за адресою: "
		    		            			+ "Гуртожиток № 7 вул. Металістів, 3";
									try{
										email.sendMessage(student.geteMail(), emailText);
										logger.info("Лист успішно надісланий");
									}catch (MessagingException me) {
										logger.error("Листа відправити неможливо: " + me);
									}
									logger.info("Запис до файлу інформації про заселеного студента до гуртожитку");
									hs.writeToFile(st, freeRoom, hostel);
									logger.info("Оновлення даних для кімнати: " + freeRoom.getId_room()
												+ "Вільного місця: " + freeRoom.getFree_in_room());
									rs.updateRoom(freeRoom, st.getMale_female());
									logger.info("Кімнату оновлено: " + freeRoom.getId_room()
												+ "Вільного місця: " + freeRoom.getFree_in_room());
									result = "Вас заселенно в гуртожиток №: " + hostel.getId_hostel() + " в кімнату: " 
														+ freeRoom.getId_room() + ". Детальніше про оплату в листі, "
														+ "який надісланий на: " + student.geteMail();
									logger.info(result);
									modelAndView.addObject("message", result);
									modelAndView.setViewName("result-search");
								}	//Студент є у базі перепусток
							}	//Для данної статі є вільне місце
						}	//Студент не забороненний до поселення
					}	//Сесії співпадають
				}	//Студент існує
			}	//Коректні ім'я та email
		}	//Перевірка даних на перші літери та коректність e-mail
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}
	
	@RequestMapping(value = "/hostel")
    public ModelAndView hostelPage(Principal principal, @ModelAttribute("inputHostel") Hostel h){
    	
		String currName = principal.getName();
    	int numHostel;
    	Hostel hostel;
    	ModelAndView model = new ModelAndView();
    	if(currName.equals("do_it")){
    		numHostel = 18;
    		hostel = hs.getHostelById(numHostel);
    		model.addObject("hostel", hostel.getId_hostel());
    		model.setViewName("hostel");
    	}else{
    		if(currName.equals("true_login")){
    			numHostel = 19;
        		hostel = hs.getHostelById(numHostel);
        		model.addObject("hostel", hostel.getId_hostel());
        		model.setViewName("hostel");
    		} else{
    			if(currName.equals("god_is_here")){
    				model.addObject("hostel", h.getId_hostel());
    	    		model.setViewName("hostel");
    			}
    		}
    	}
    	return model;
    }
	
	@RequestMapping(value = "/hostel/message", method = RequestMethod.GET)
	public String messageToHostel(Model model, @ModelAttribute("hostel") Hostel h){
		
		String result = hs.readFile(h.getId_hostel());
		model.addAttribute("result", result);
		return "message";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model mod){
		
		return "admin";
	}
	
	@RequestMapping(value = "/admin/chooseHostel", method = RequestMethod.GET)
	public ModelAndView chooseHostel(Model mod){
		
		ModelAndView model = new ModelAndView();
		List<Integer> allId = hs.getAllIdHostel();
		mod.addAttribute("id_hostel", allId);
		model.addObject("inputHostel", new Hostel());
		model.setViewName("chooseHostel");
		return model;
	}
	
	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public ModelAndView showAllGroups(){
		
		ModelAndView model = new ModelAndView();
		List<Group> list_group = gs.getAllGroup();
		model.addObject("all_group", list_group);
		model.setViewName("department");
		return model;
	}
	
	@RequestMapping(value = "/department/addGroup", method = RequestMethod.GET)
	public ModelAndView addGroup(){
		
		ModelAndView model = new ModelAndView();
		model.addObject("addGroup", new Group());
		model.setViewName("addGroup");
		return model;
	}
	
	@RequestMapping(value="/department/added-group")
	public String addedgroup(@ModelAttribute("addGroup") Group group, Model mod){
	
		logger.info("Нова група: " + group);
		gs.addGroup(group);
		logger.info("Групу додано!");
		mod.addAttribute("addedGroup", group);
		return "added-group";
	}
	
	@RequestMapping(value = "/department/updateGroup", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value="id", required=true) String id, 
              Model mod) {
		
		logger.info("Змінення групи: " + id);
		mod.addAttribute("updateGroup", gs.getGroup(id));
		return "updateGroup";
	}
	
	@RequestMapping(value = "/department/updateGroup", method = RequestMethod.POST)
    public String getEdit(@ModelAttribute("updateGroup") Group group,
    		@RequestParam(value="id", required=true) String id, 
            Model mod) {
		
		Group updatedGroup = gs.updateGroup(group, id);
		logger.info("Зміненні дані групи " + id + ": " + updatedGroup);
		mod.addAttribute("updated", updatedGroup);
		return "updatedGroup";
	}
	
	@RequestMapping(value = "/department/show", method = RequestMethod.GET)
	public ModelAndView showStudentFromGroup(
			@RequestParam(value="id", required=true) String id, Model mod){
		
		ModelAndView model = new ModelAndView();
		List<Student> student = ss.getStudentFromGroup(id);
		mod.addAttribute("id", id);
		model.addObject("students", student);
		model.setViewName("show");
		return model;
	}
	
	@RequestMapping(value = "/department/show/addStudent", method = RequestMethod.GET)
    public String addStudent(@RequestParam(value="id", required=true) String id, 
              Model mod) {
		
		Student newStudent = new Student();
		mod.addAttribute("id", id);
		ArrayList<String> gender = new ArrayList<>(2);
		gender.add("ч");
		gender.add("ж");
		mod.addAttribute("gender", gender);
		ArrayList<Boolean> choose = new ArrayList<>(2);
		choose.add(true);
		choose.add(false);
		mod.addAttribute("choose", choose);
		mod.addAttribute("addStudent", newStudent);
		return "addStudent";
	}
	
	@RequestMapping(value = "/department/show/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("addStudent") Student student, 
    		@RequestParam(value="id", required=true) String id,
    		Model mod) {
		
		mod.addAttribute("id", id);
		logger.info("Додавання нового студента!");
		Group selectedGroup = gs.getGroup(id);
		student.setId_group(selectedGroup);
		ss.addStudent(student);
		logger.info("Новий студент: " + student);
		if(student.isNeed_hostel() == true){
			logger.info("Необхідність гуртожитку == true");
			boolean prohibited = ps.isProhibited(student);
			AccessToHostel accessToHostel = access.existsInDB(student);
			EvictedStudent evictedStudent = ess.studentIsEvicted(student);
			if(prohibited){
				logger.info("Студент заборонений до поселення");
			}else{
				logger.info("Студент не заборонений до поселення");
				logger.info("Пошук вільного місця..........");
				Room freeRoom = rs.getFreeRoom(student);
				if(freeRoom == null){
					logger.info("Немає вільного місця для данної статі");
				}else{
					Hostel hostel = hs.getIdHostelFromRoom(freeRoom);
					logger.info("Для данної статі є вільне місце");
					logger.info("Студент є у базі перепусток?");
					if(accessToHostel == null){
						logger.info("Студента у базі перепусток немає");
						logger.info("Номер гуртожитку: " + hostel.getId_hostel());
						accessToHostel = access.newAccess(student, hostel);
						logger.info("№ нового пропуск: " + accessToHostel.getId_access());
						inhabited.inhabiteStudent(accessToHostel, freeRoom, student);
						logger.info("Студента заселено у кімнату: " + freeRoom.getId_room());
						logger.info("Запис до файлу інформації про заселеного студента до гуртожитку");
						hs.writeToFile(student, freeRoom, hostel);
						logger.info("Оновлення даних для кімнати: " + freeRoom.getId_room()
									+ "Вільного місця: " + freeRoom.getFree_in_room());
						rs.updateRoom(freeRoom, student.getMale_female());
						int upd = freeRoom.getFree_in_room()-1;
						logger.info("Кімнату оновлено: " + freeRoom.getId_room()
									+ " Вільного місця: " + upd);
						if(evictedStudent != null){
							ess.deleteEvictedStudent(evictedStudent);
							logger.info("Студента видалено із таблиці \"Виселенні студенти\"!");
						}
					}else{
						logger.info("Студент є у базі перепусток");
						logger.info("Номер гуртожитку: " + hostel.getId_hostel());
						String result = "Старі дані пропуску: " + accessToHostel.getId_access() + ", " 
								  + accessToHostel.getAccess_end() + ", " + accessToHostel.getAccess_start()
								  + ", " + accessToHostel.getAccess_group() + ", "
								  + accessToHostel.getAccess_hostel() + ", " 
								  + accessToHostel.getAccess_student();
						logger.info(result);
						AccessToHostel updateAccess = access.newAccess(student, hostel);
						result = "Оновлені дані пропуску: " + updateAccess.getId_access() + ", " 
								  + updateAccess.getAccess_end() + ", " + updateAccess.getAccess_start()
								  + ", " + updateAccess.getAccess_group() + ", "
								  + updateAccess.getAccess_hostel() + ", " 
								  + updateAccess.getAccess_student();
						logger.info(result);
						inhabited.inhabiteStudent(updateAccess, freeRoom, student);
						logger.info("Студента заселено у кімнату: " + freeRoom.getId_room());
						logger.info("Запис до файлу інформації про заселеного студента до гуртожитку");
						hs.writeToFile(student, freeRoom, hostel);
						logger.info("Оновлення даних для кімнати: " + freeRoom.getId_room()
									+ "Вільного місця: " + freeRoom.getFree_in_room());
						rs.updateRoom(freeRoom, student.getMale_female());
						int upd = freeRoom.getFree_in_room()-1;
						logger.info("Кімнату оновлено: " + freeRoom.getId_room()
									+ " Вільного місця: " + upd);
					}//Перепустка
				}//Вільне місце
			}//Студент не забороненний
		}
		mod.addAttribute("student", student);
		return "addedStudent";
	}
	
	@RequestMapping(value = "/department/show/updateStudent", method = RequestMethod.GET)
	public String updateStudent(@RequestParam(value = "id", required = true) String id, 
								Model mod){

		mod.addAttribute("id", id);
		Student updateStudent = ss.getStudent(id);
		List<Group> listGroup = gs.getAllGroup();
		logger.info("Змінення даних студента: " + updateStudent.getFull_name());
		ArrayList<String> gender = new ArrayList<>(2);
		gender.add("ч");
		gender.add("ж");
		mod.addAttribute("gender", gender);
		/*ArrayList<Boolean> choose = new ArrayList<>(2);
		choose.add(true);
		choose.add(false);
		mod.addAttribute("choose", choose);*/
		mod.addAttribute("groups", listGroup);
		mod.addAttribute("updateStudent", updateStudent);
		return "updateStudent";
	}
	
	@RequestMapping(value = "/department/show/updateStudent", method = RequestMethod.POST)
	public String updatedStudent(@ModelAttribute("updateStudent") Student update,
			@RequestParam(value="id", required=true) String id, Model mod){
		
		System.out.println("Checkbox from jsp: " + update.isNeed_hostel());
		Student oldStudent = ss.getStudent(id);
		boolean oldNeedHostel = oldStudent.isNeed_hostel(); 
		Group oldGroup = oldStudent.getId_group();
		Student upStud = ss.updateStudent(update, id);
		System.out.println("need_hostel from update: " + upStud.isNeed_hostel());
		logger.info("Дані про студента змінено!");
		mod.addAttribute("updatedStudent", upStud);
		Group curGroup = upStud.getId_group();
		boolean prohibited = ps.isProhibited(upStud);
		AccessToHostel accessToHostel = access.existsInDB(upStud);
		EvictedStudent evictedStudent = ess.studentIsEvicted(upStud);
		if(curGroup.getId_group().equals(oldGroup.getId_group())){
			logger.info("Групи однакові");
		}else{
			logger.info("Групи не однакові!");
			logger.info("Заміна групи для решти таблиць");
			if(accessToHostel != null){
				access.updateAccess(accessToHostel, upStud);
				logger.info("Таблицю \"Перепустка\" змінено");
			}
			if(evictedStudent != null){
				ess.updateEvictedStudent(evictedStudent, upStud);
				logger.info("Таблицю \"Виселенні студенти\" зміненно");
			}
			if(prohibited){
				ps.updateProhibited(upStud);
				logger.info("Таблицю \"Забороненні\" зміненно");
			}
		}
		logger.info("Необхідність гуртожитку змінилась ?");
		System.out.println("Old: " + oldNeedHostel);
		System.out.println("New: " + upStud.isNeed_hostel());
		if(oldNeedHostel != upStud.isNeed_hostel()){
			logger.info("Змінилась Необхідніть гуртожитку!");
			if(upStud.isNeed_hostel() == true){
				logger.info("Необхідність гуртожитку == true");
				if(prohibited){
					logger.info("Студент заборонений до поселення");
				}else{
					logger.info("Студент не заборонений до поселення");
					logger.info("Пошук вільного місця..........");
					Room freeRoom = rs.getFreeRoom(upStud);
					if(freeRoom == null){
						logger.info("Немає вільного місця для данної статі");
					}else{
						Hostel hostel = hs.getIdHostelFromRoom(freeRoom);
						logger.info("Для данної статі є вільне місце");
						logger.info("Студент є у базі перепусток?");
						if(accessToHostel == null){
							logger.info("Студента у базі перепусток немає");
							logger.info("Номер гуртожитку: " + hostel.getId_hostel());
							accessToHostel = access.newAccess(upStud, hostel);
							logger.info("№ нового пропуск: " + accessToHostel.getId_access());
							inhabited.inhabiteStudent(accessToHostel, freeRoom, upStud);
							logger.info("Студента заселено у кімнату: " + freeRoom.getId_room());
							logger.info("Запис до файлу інформації про заселеного студента до гуртожитку");
							hs.writeToFile(upStud, freeRoom, hostel);
							logger.info("Оновлення даних для кімнати: " + freeRoom.getId_room()
										+ "Вільного місця: " + freeRoom.getFree_in_room());
							rs.updateRoom(freeRoom, upStud.getMale_female());
							int upd = freeRoom.getFree_in_room()-1;
							logger.info("Кімнату оновлено: " + freeRoom.getId_room()
										+ " Вільного місця: " + upd);
							if(evictedStudent != null){
								ess.deleteEvictedStudent(evictedStudent);
								logger.info("Студента видалено із таблиці \"Виселенні студенти\"!");
							}
						}else{
							logger.info("Студент є у базі перепусток");
							logger.info("Номер гуртожитку: " + hostel.getId_hostel());
							String result = "Старі дані пропуску: " + accessToHostel.getId_access() + ", " 
									  + accessToHostel.getAccess_end() + ", " + accessToHostel.getAccess_start()
									  + ", " + accessToHostel.getAccess_group() + ", "
									  + accessToHostel.getAccess_hostel() + ", " 
									  + accessToHostel.getAccess_student();
							logger.info(result);
							AccessToHostel updateAccess = access.newAccess(upStud, hostel);
							result = "Оновлені дані пропуску: " + updateAccess.getId_access() + ", " 
									  + updateAccess.getAccess_end() + ", " + updateAccess.getAccess_start()
									  + ", " + updateAccess.getAccess_group() + ", "
									  + updateAccess.getAccess_hostel() + ", " 
									  + updateAccess.getAccess_student();
							logger.info(result);
							inhabited.inhabiteStudent(updateAccess, freeRoom, upStud);
							logger.info("Студента заселено у кімнату: " + freeRoom.getId_room());
							logger.info("Запис до файлу інформації про заселеного студента до гуртожитку");
							hs.writeToFile(upStud, freeRoom, hostel);
							logger.info("Оновлення даних для кімнати: " + freeRoom.getId_room()
										+ "Вільного місця: " + freeRoom.getFree_in_room());
							rs.updateRoom(freeRoom, upStud.getMale_female());
							int upd = freeRoom.getFree_in_room()-1;
							logger.info("Кімнату оновлено: " + freeRoom.getId_room()
										+ " Вільного місця: " + upd);
						}//Перепустка
					}//Вільне місце
				}//Студент не забороненний
			}else{
				logger.info("Необхідність гуртожитку == false");
				InhabitedStudent inhabitedStudent = inhabited.getInhabitedStudent(upStud);
				Room room = inhabitedStudent.getInhabited_room();
				inhabited.deleteInhabitedStudent(inhabitedStudent);
				logger.info("Студента видалено із таблиці \"Заселенні студенти\"");
				access.deleteAccess(accessToHostel);
				logger.info("Студента видалено із таблиці \"Перепустка\"");
				rs.incrementRoom(room);
				logger.info("Вільне місце в кімнаті " + room.getId_room() + " збільшено!");
				Hostel hostel = hs.getIdHostelFromRoom(room);
				evictedStudent = ess.addEvictedStudent(upStud, hostel);
				logger.info("Студента додано до таблиці \"Виселенні студенти\", причина: " 
																+ evictedStudent.getReason());
			}
		}
		return "updatedStudent";
	}
	
}