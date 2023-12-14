package ru.company.hr;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.NoArgsConstructor;
import ru.company.hr.repository.EmployeeRepository;
import ru.company.hr.entity.Employee;
import ru.company.hr.entity.EmployeeBase;
import ru.company.hr.entity.Manager;
import ru.company.hr.entity.Salesman;

@NoArgsConstructor
@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {
	  
	  EmployeeBase emp1 = new Employee("Сергеев Сотрудник Сергеевич");
	  emp1.setId(1);
	  emp1.setDateOfEmployment(LocalDate.of(2017, 7, 7));
	  emp1.setBasicRate(BigDecimal.valueOf(2000000L, 2));
	  
	  EmployeeBase emp2 = new Salesman("Петров Продавец Петрович");
	  emp2.setId(2);
	  emp2.setDateOfEmployment(LocalDate.of(2016, 6, 6));
	  emp2.setBasicRate(BigDecimal.valueOf(3000000L, 2));
	  
		  EmployeeBase emp3 = new Manager("Николаев Менеджер Николаевич");
		  emp3.setId(3);
		  emp3.setDateOfEmployment(LocalDate.of(2015, 5, 5));
		  emp3.setBasicRate(BigDecimal.valueOf(4000000L, 2));
		  emp3.setSupervisor(emp2);
		  
			  EmployeeBase emp5 = new Manager("Михайлов Менеджер Михайлович");
			  emp5.setId(5);
			  emp5.setDateOfEmployment(LocalDate.of(2018, 8, 8));
			  emp5.setBasicRate(BigDecimal.valueOf(2100000L, 2));
			  emp5.setSupervisor(emp3);
			  
				  EmployeeBase emp6 = new Manager("Малютина Менеджер Дмитриевна");
				  emp6.setId(6);
				  emp6.setDateOfEmployment(LocalDate.of(2020, 8, 8));
				  emp6.setBasicRate(BigDecimal.valueOf(2300000L, 2));
				  emp6.setSupervisor(emp5);
	  
		  EmployeeBase emp4 = new Employee("Александров Сотрудник Александрович");
		  emp4.setId(4);
		  emp4.setDateOfEmployment(LocalDate.of(2019, 9, 9));
		  emp4.setBasicRate(BigDecimal.valueOf(2200000L, 2));
		  emp4.setSupervisor(emp2);
	  
	  
    return args -> {
      /*log.info("Preloading " + repository.save(emp1));
      log.info("Preloading " + repository.save(emp2));
      log.info("Preloading " + repository.save(emp3));
      log.info("Preloading " + repository.save(emp4));
      log.info("Preloading " + repository.save(emp5));
      log.info("Preloading " + repository.save(emp6));*/
      
    };
  }
}