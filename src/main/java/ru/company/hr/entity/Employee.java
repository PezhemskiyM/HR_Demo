package ru.company.hr.entity;

import java.util.List;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import ru.company.hr.Group;
import ru.company.hr.GroupEmployee;

import org.springframework.stereotype.Component;

@Entity
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "Employee")
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private long id;
	
	@NotNull
    @Column(name = "NAME")
	private String name;
	
	@NotNull
    @Column(name = "DATE_OF_EMPLOYMENT")
	private LocalDate dateOfEmployment;
	
	@NotNull
    @Column(name = "GROUP")
	private Group group;
	
    @Column(name = "BASIC_RATE")
    @Min(value=0, message = "the base rate cannot be negative")
	private long basicRate;
    
    @Column(name = "SUPERSIVOR")
	private Employee supervisor;
    
    private List<Employee> subordinates;
    
	//public Employee() {}
    
    public Employee(String name, LocalDate dateOfEmployment, Group group, long basicRate, Employee supervior, List<Employee> subordinates) {
		this.name = name;
		this.dateOfEmployment = dateOfEmployment;
		this.group = group;
		this.basicRate = basicRate * 100;	
		this.supervisor = supervior;
		this.subordinates = subordinates;
	}
    
	public Employee(String name, LocalDate dateOfEmployment, Group group, long basicRate, Employee supervior) {
		this.name = name;
		this.dateOfEmployment = dateOfEmployment;
		this.group = group;
		this.basicRate = basicRate * 100;	
		this.supervisor = supervior;
	}
	
	public Employee(String name, LocalDate dateOfEmployment, Group group, long basicRate) {
		this.name = name;
		this.dateOfEmployment = dateOfEmployment;
		this.group = group;
		this.basicRate = basicRate * 100;
	}
	
	public Employee(String name) {
		this.name = name;
		this.dateOfEmployment = LocalDate.now();
		this.group = new GroupEmployee();
		this.basicRate = 0;
		
	}
	
	public long getSalary() {
		LocalDate reportDate = LocalDate.now();
		Period period = dateOfEmployment.until(reportDate);
		int experience = period.getYears();
        //System.out.println("Стаж сотрудника: " + experience + " лет");
		 
		long salary = group.calculateSalary(basicRate, experience);
		//System.out.println("salary (" + reportDate + "): " + (salary / 100f));
		return salary;
	}
}
