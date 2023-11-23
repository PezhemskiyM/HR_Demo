package ru.company.hr;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.websocket.Session;


@EnableJpaRepositories("ru.company.hr.repository")
@ComponentScan(basePackages = { "ru.company.hr" })
//@ComponentScan("ru.company.hr")
@EntityScan("ru.company.hr.entity") 
@SpringBootApplication
public class MainClass{
	private static ApplicationContext applicationContext;

  public static void main(String... args) {
	//SessionFactory sf = HibernateUtil.getSessionFactory();
    //Session session = sf.openSession();
    applicationContext = SpringApplication.run(MainClass.class, args);
    checkBeansPresence(
        "Employee", "mainClass");
  }

  private static void checkBeansPresence(String... beans) {
      for (String beanName : beans) {
          System.out.println("Is " + beanName + " in ApplicationContext: " + 
            applicationContext.containsBean(beanName));
      }
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
