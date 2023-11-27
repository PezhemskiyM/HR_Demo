package ru.company.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("ru.company.hr.repository")
@ComponentScan(basePackages = { "ru.company.hr" })
@EntityScan("ru.company.hr.entity") 
@SpringBootApplication
public class MainClass{
	private static ApplicationContext applicationContext;

  public static void main(String... args) {
	//SessionFactory sf = HibernateUtil.getSessionFactory();
    //Session session = sf.openSession();
    applicationContext = SpringApplication.run(MainClass.class, args);
    checkBeansPresence(
        "EmployeeBase", "mainClass");
  }

  private static void checkBeansPresence(String... beans) {
      for (String beanName : beans) {
          System.out.println("Is " + beanName + " in ApplicationContext: " + 
            applicationContext.containsBean(beanName));
      }
  }
}
