package ru.company.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainClass{

  public static void main(String... args) {
    SpringApplication.run(MainClass.class, args);
  }
}

/*
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.time.LocalDate;
public class TestSpring {
	public static void main(String[] args) {
		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
		Group group = context.getBean("groupBean", Group.class);
		
		Employee employer = new Employee("Piter", LocalDate.of(2017, 01, 10), group, 17452);
		//Employee employer = context.getBean("employeeBean", Employee.class);
		employer.getSalary();
		//employer.getName();
		
		//System.out.println(group.getSalary());
		context.close();*//*
		
		
		
	}
}*/
