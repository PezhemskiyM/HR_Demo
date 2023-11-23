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

@NoArgsConstructor
@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", LocalDate.of(2017, 12, 7), BigDecimal.valueOf(20000L, 2))));
      log.info("Preloading " + repository.save(new Employee("Frodo Baggins")));
    };
  }
}