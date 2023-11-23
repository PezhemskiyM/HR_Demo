package ru.company.hr;

import java.math.BigDecimal;
import java.time.LocalDate;

import ru.company.hr.entity.EmployeeBase;

public interface IAbstractEmployeeService {

	BigDecimal calculateSalary(EmployeeBase employee, LocalDate date);
	
}
