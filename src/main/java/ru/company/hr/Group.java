package ru.company.hr;

import ru.company.hr.entity.Employee;

public interface Group {
	public long calculateSalary(Employee employee, long basicRate, int experience);
	
	public long calculateSalary(long basicRate, int experience);
}
