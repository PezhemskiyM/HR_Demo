package ru.company.hr.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.NoArgsConstructor;
import ru.company.hr.repository.EmployeeRepository;
//import ru.company.hr.repository.AutoUserRepository;
//import ru.company.hr.repository.OrderRepository;
//import ru.company.service.MenuService;
import ru.company.hr.entity.Employee;
import ru.company.hr.throwsClasses.EmployeeNotFoundException;

//@NoArgsConstructor
@RestController
@RequestMapping("/")
public class EmployeeController {
	//@Autowired
	private final EmployeeRepository repository;
    //private EmployeeRepository employeeRepository;
  
	/*@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AutoUserRepository userRepository;
    
	@Autowired
	private MenuService menuService;
   
	@Autowired
	private MOTRepository mOTRepository;*/
    
	
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
	  List<Employee> all() {
	    return repository.findAll();
	  }

	  @PostMapping("/employees")
	  Employee newEmployee(@RequestBody Employee newEmployee) {
	    return repository.save(newEmployee);
	  }

	  // Single item
	  
	  @GetMapping("/employees/{id}")
	  Employee one(@PathVariable long id) {
	    
	    return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	  }

	  @PutMapping("/employees/{id}")
	  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable long id ) {
	   
	    return repository.findById(id)
	      .map(employee -> {
	        employee.setName(newEmployee.getName());
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
	  
	  @GetMapping("/employees/salary{id}")
	  Employee getSalary(@PathVariable long id) {
	    
	    return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	  }
	  
}

 
