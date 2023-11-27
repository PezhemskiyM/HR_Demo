package ru.company.hr.controllers;

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
	  List<EmployeeBase> all() {
	    return repository.findAll();
	  }

	  @PostMapping("/employees")
	  EmployeeBase newEmployee(@RequestBody EmployeeBase newEmployee) {
	    return repository.save(newEmployee);
	  }

	  // Single item
	  
	  @GetMapping("/employees/{id}")
	  EmployeeBase one(@PathVariable long id) {
	    
	    return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	  }

	  @PutMapping("/employees/{id}")
	  EmployeeBase replaceEmployee(@RequestBody EmployeeBase newEmployee, @PathVariable long id ) {
	   
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
	  EmployeeBase getSalary(@PathVariable long id) {
	    
	    return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	  }
	  
}

 
