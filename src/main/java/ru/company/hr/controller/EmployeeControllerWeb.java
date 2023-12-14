package ru.company.hr.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import ru.company.hr.repository.EmployeeRepository;
import ru.company.hr.entity.Employee;
import ru.company.hr.entity.EmployeeBase;
import ru.company.hr.entity.Manager;
import ru.company.hr.entity.Salesman;
import ru.company.hr.throwsClasses.EmployeeNotFoundException;

@Controller
@RequestMapping("/")
public class EmployeeControllerWeb {

	private final EmployeeRepository repository;
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeControllerWeb(EmployeeRepository repository) {
		this.repository = repository;
	}
	  
	@RequestMapping(value = "/main")
	public String goHome(Model model){
		model.addAttribute("employees", repository.findBySupervisor(null));
	    return "main";
	}
	   
	@RequestMapping("/new")
	public String goHomes(){
		return "new";
	}
	
	@PostMapping(value = "/newWeb",consumes = {"application/json", "application/x-www-form-urlencoded" })
	public String newEmployeeWeb(
							@RequestParam("name") String name, 
							@RequestParam("dateOfEmployment") LocalDate dateOfEmployment, 
							@RequestParam("basicRate") long basicRate,
							@RequestParam("group") String group,
							@RequestParam("supervisor") long id){
		log.info("Начинаем добавление сотрудника");
		EmployeeBase newEmployee;
		if (group.equals("EMPLOYEE")){
			newEmployee = new Employee();
		}  
		else if (group.equals("MANAGER")){
			newEmployee = new Manager();
		} 
		else if (group.equals("SALESMAN")){
			newEmployee = new Salesman();
		}
		else {
			return null;
		}
		
		newEmployee.setName(name);
		newEmployee.setDateOfEmployment(dateOfEmployment);
		newEmployee.setBasicRate(BigDecimal.valueOf(basicRate));
		if (id != 0) {
			EmployeeBase supervisor = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
			newEmployee.setSupervisor(supervisor);	
		}
		
		log.info("Присвоили нужные поля сотруднику " + newEmployee.getName());
		repository.save(newEmployee);
		return "redirect:/main";
	}
	  
}

 
