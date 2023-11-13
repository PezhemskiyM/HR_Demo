package ru.company.hr;

import java.time.LocalDate;

public class Employee {
	private String name;
	private LocalDate dateOfEmployment;
	private Group group;
	private int basicRate;
	private Employee supervisor;
	
	public Employee(String name, LocalDate dateOfEmployment, Group group, int basicRate, Employee supervior) {
		this.name = name;
		this.dateOfEmployment = dateOfEmployment;
		this.group = group;
		this.basicRate = basicRate;	
		this.supervisor = supervior;
	}
	
	public Employee(String name, LocalDate dateOfEmployment, Group group, int basicRate) {
		this.name = name;
		this.dateOfEmployment = dateOfEmployment;
		this.group = group;
		this.basicRate = basicRate;	
	}
	
	public Employee(String name) {
		this.name = name;
		this.dateOfEmployment = LocalDate.now();
		this.group = new GroupEmployee();
		this.basicRate = 50000;
		
	}
	
	public int getSalary() {
		LocalDate reportDate = LocalDate.now();
		//System.out.println("current salary: " + group.getSalary(basicRate, dateOfEmployment, reportDate));
		int salary = group.getSalary();
		System.out.println("salary (" + reportDate + "): " + salary);
		return salary;
	}
}
