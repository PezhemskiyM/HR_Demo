package ru.company.hr.entity;

import java.util.Collections;
import java.util.List;

import jakarta.persistence.Entity;
import ru.company.hr.EmployeeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Employee extends EmployeeBase {
	
	private List<EmployeeBase> list;

    public Employee() {
        super();
        super.setGroup(EmployeeEnum.EMPLOYEE);
        //list = List.of();
        list = Collections.unmodifiableList(list);
        super.setSubordinates(list);
        super.setDateOfEmployment(LocalDate.now());
    }
    
    public Employee(String name) {
        super();
        super.setGroup(EmployeeEnum.EMPLOYEE);
        super.setName(name);
        //list = List.of();
        list = Collections.unmodifiableList(list);
        super.setSubordinates(list);
        super.setDateOfEmployment(LocalDate.now());
    }
    
    public Employee(LocalDate employmentDate) {
        this();
        super.setDateOfEmployment(employmentDate);
    }
    
    public Employee(String name, LocalDate dateofEmployment, BigDecimal basicRate) {
    	super();
    	super.setGroup(EmployeeEnum.EMPLOYEE);
    	
}

    @Override
    public void setSubordinates(List<EmployeeBase> subordinates) {
        super.setSubordinates(list);
    }

    @Override
    public List<EmployeeBase> getSubordinates() {
        return null;
    }
    
}
