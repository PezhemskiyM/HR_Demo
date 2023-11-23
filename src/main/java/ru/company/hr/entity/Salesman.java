package ru.company.hr.entity;

import java.util.List;

import jakarta.persistence.Entity;
import ru.company.hr.EmployeeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Salesman extends EmployeeBase {
	
	public Salesman() {
        super();
        super.setGroup(EmployeeEnum.SALESMAN);
        super.setDateOfEmployment(LocalDate.now());
    }
	 
	public Salesman(String name) {
        super();
        super.setName(name);
        super.setGroup(EmployeeEnum.SALESMAN);
        super.setDateOfEmployment(LocalDate.now());
    }
	
	public Salesman(String name, LocalDate dateofEmployment, BigDecimal basicRate) {
    	super();
    	super.setGroup(EmployeeEnum.SALESMAN);
    	
}
	
    public Salesman(LocalDate employmentDate, List<EmployeeBase> employees) {
        this();
        super.setDateOfEmployment(employmentDate);
        super.setSubordinates(employees);
    }
}
