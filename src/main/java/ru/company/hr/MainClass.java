package ru.company.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("ru.company.hr.repository")
@ComponentScan(basePackages = { "ru.company.hr" })
@EntityScan("ru.company.hr.entity") 
@SpringBootApplication
public class MainClass{

  public static void main(String... args) {
    SpringApplication.run(MainClass.class, args);
  }

}
