package ru.company.hr.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.company.hr.repository.EmployeeRepository;
import ru.company.hr.entity.EmployeeBase;
import ru.company.hr.throwsClasses.EmployeeNotFoundException;

//@NoArgsConstructor
@RestController
@RequestMapping("/")
public class EmployeeController {
	//@Autowired
	private final EmployeeRepository repository;
	
	@Autowired
	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	  
	@RequestMapping("/")
	public String goHome(){
	    return "index";
	}
	   
	@RequestMapping("/index")
	public String goHomes(){
		return "index";
	}

	@GetMapping("/employees")
	List<EmployeeBase> getAll() {
		return repository.findAll();
	}

	@PostMapping("/employees")
	EmployeeBase newEmployee(@RequestBody EmployeeBase newEmployee) {
		return repository.save(newEmployee);
	}

	// Single item
  
	@GetMapping("/employees/{id}")
	EmployeeBase getEmployeeByID(@PathVariable long id) {
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@PutMapping("/employees/{id}")
	EmployeeBase replaceEmployee(@RequestBody EmployeeBase newEmployee, @PathVariable long id ) {
   
	    return repository.findById(id)
	      .map(employee -> {
	        employee.setName(newEmployee.getName());
	        employee.setDateOfEmployment(newEmployee.getDateOfEmployment());
	        employee.setBasicRate(newEmployee.getBasicRate());
	        employee.setSupervisor(newEmployee.getSupervisor());
	        employee.setGroup(newEmployee.getGroup());
	        return repository.save(employee);
	      })
	      .orElseGet(() -> {
	        newEmployee.setId(id);
	        return repository.save(newEmployee);
	      });
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable long id) {
		repository.deleteById(id);
	}
  
	@GetMapping("/employees/salary/{id}")
	BigDecimal getSalaryByID(@PathVariable long id) {
		//EmployeeBase emp = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		EmployeeBase emp = getEmployeeByID(id);
		BigDecimal salaryEmp = emp.calculateSalary(emp, LocalDate.now());
		System.out.println(emp.getName() + " " + salaryEmp);
		return salaryEmp;
	}
	
	@GetMapping("/employees/salary/all")
	BigDecimal getSalaryAll() {
		BigDecimal salary = BigDecimal.ZERO;
		List<EmployeeBase> listEmp = getAll();
		for(EmployeeBase emp2: listEmp) {
			BigDecimal salaryEmp = emp2.calculateSalary(emp2, LocalDate.now());
			System.out.println(emp2.getName() + " " + salaryEmp);
			salary = salary.add(salaryEmp);
		}
		//EmployeeBase emp = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		//return emp.calculateSalary(emp, LocalDate.now());
		return salary;
	}
	
	  
}

 
