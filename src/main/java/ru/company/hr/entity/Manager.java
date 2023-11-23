package ru.company.hr.entity;

import java.util.List;

import jakarta.persistence.Entity;
import ru.company.hr.EmployeeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Manager extends EmployeeBase {
	
	public Manager() {
        super();
        super.setGroup(EmployeeEnum.MANAGER);
        super.setDateOfEmployment(LocalDate.now());
    }
	 
	public Manager(String name) {
        super();
        super.setName(name);
        super.setGroup(EmployeeEnum.MANAGER);
        super.setDateOfEmployment(LocalDate.now());
    }
	 
	public Manager(String name, LocalDate dateofEmployment, BigDecimal basicRate) {
	    	super();
	    	super.setGroup(EmployeeEnum.MANAGER);
	    	
	}

    public Manager(LocalDate employmentDate, List<EmployeeBase> employees) {
        this();
        super.setDateOfEmployment(employmentDate);
        super.setSubordinates(employees);
    }
	    
}
