package ru.company.hr.entity;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Entity;
import ru.company.hr.EmployeeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@JsonDeserialize(as = Employee.class)
public class Employee extends EmployeeBase {

    public Employee() {
        super();
        super.setGroup(EmployeeEnum.EMPLOYEE);
        super.setDateOfEmployment(LocalDate.now());
    }
    
    public Employee(String name) {
        super();
        super.setGroup(EmployeeEnum.EMPLOYEE);
        super.setName(name);
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
        //Ничего не делаем. У Employee не бывает подчиненных
    }

    @Override
    public List<EmployeeBase> getSubordinates() {
        return null;
    }
    
}
