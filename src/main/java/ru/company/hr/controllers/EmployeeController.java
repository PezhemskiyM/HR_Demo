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

import org.springframework.boot.SpringApplication;
import ru.company.hr.repository.EmployeeRepository;
//import ru.company.hr.repository.AutoUserRepository;
//import ru.company.hr.repository.OrderRepository;
//import ru.company.service.MenuService;
import ru.company.hr.Employee;
import ru.company.hr.throwsClasses.EmployeeNotFoundException;

@NoArgsConstructor
@RestController
@RequestMapping("/")
public class EmployeeController {
	//@Autowired
	private EmployeeRepository repository;
    //private EmployeeRepository employeeRepository;
  
	/*@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AutoUserRepository userRepository;
    
	@Autowired
	private MenuService menuService;
   
	@Autowired
	private MOTRepository mOTRepository;*/
    
	
	//@Autowired
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
	  // Aggregate root
	  // tag::get-aggregate-root[]
	  @GetMapping("/employees")
	  List<Employee> all() {
	    return repository.findAll();
	  }
	  // end::get-aggregate-root[]

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
	  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable long id) {
	   
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
	  
	  
	
	
	/*@RequestMapping("/")
    public String goHome(){
        return "home";
    }
   
	@RequestMapping("/index")
	public String goHomes(){
		return "home";
	}   
	@RequestMapping(value = "/makeorder", method = RequestMethod.GET)
	public String addOrder(Model model) {
		model.addAttribute("order", new Order());
        return "makeorder";
    }
    
    
     @RequestMapping(value="/itemtypes")
   public @ResponseBody List<String> getBookTypes(){
        return new ArrayList(Arrays.asList("pizza", "drink", "sauce" , "burger", "snack", "tea", "coffee", "dessert"));
    }
    
    @RequestMapping(value="/seeorder")
    public String goSeeOrder(Model model, Pageable page, Sort sort){
  model.addAttribute("page", mOTRepository.findAll(page));
  
   //model.addAttribute("filter", employeeRepository.findByTypeContaining(type));
    model.addAttribute("orders", new MenuOrderTable());
   return "seeorder";
   
   }
    
    @RequestMapping(value="/additem",method=RequestMethod.GET)
    public String addItem(Model model){
        model.addAttribute("item",new MenuItem());
    return "additem";
    }*/
    
    /*@RequestMapping(value="/addemployee",method=RequestMethod.POST)
    public String saveItem(
        @Valid 
        @ModelAttribute Employee employee,
        Errors error, 
        Model model, 
        RedirectAttributes attr){
        	model.addAttribute("item", employee);
        	if(error.hasErrors()){
        		return "additem";
        	}
        	else{
        		employeeRepository.save(employee);
        		attr.addFlashAttribute("item",employee);
        		return "home";
        	}
    	}*/
    /*
      @InitBinder
   public void bindValidator(WebDataBinder binder){
      // binder.addValidators(new InputValidator());
       
   }
   
    
    @ResponseBody
    @RequestMapping(value="/all")
   public List<MenuItem> getAllMenu(){
  return this.employeeRepository.findAll();
   }
   
    @RequestMapping(value="/menu" , method = RequestMethod.GET)
   public String getAllBooks(Model model, Pageable page, Sort sort,String type){
   model.addAttribute("page", employeeRepository.findAll(page));
   model.addAttribute("sort",(sort!=null)?sort.iterator().next().getProperty():"");
   //model.addAttribute("filter", employeeRepository.findByTypeContaining(type));
    model.addAttribute("menut", new MenuItem());
   return "menu";
   
   }
   
     @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public String selectContinentss(@ModelAttribute String itemt, Errors error, Model model, RedirectAttributes attr) {

        model.addAttribute("itemt", new String());
       
        if (error.hasErrors()) {
            return "menu";
        } else {
            attr.addFlashAttribute("itemf", employeeRepository.findByTypeContaining("Burger"));
            return "redirect:/menu";
        }
    }

   @RequestMapping("/menu/{itemId}")
    public String getOneItem(@PathVariable("itemId") Long itemId, Model model) {
        MenuItem item = employeeRepository.findOne(itemId);
        model.addAttribute("item", item);
        menuService.saveMenuInfile(item);
        return "item";
    }*/
}

 
