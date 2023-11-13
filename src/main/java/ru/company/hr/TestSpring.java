package ru.company.hr;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.time.LocalDate;

public class TestSpring {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
		//Group group = context.getBean("groupBean", Group.class);
		
		//Employee employer = new Employee("Piter", LocalDate.now(), group, 15000);
		Employee employer = context.getBean("employee", Employee.class);
		employer.getSalary();
		
		//System.out.println(group.getSalary());
		context.close();
	}
}
