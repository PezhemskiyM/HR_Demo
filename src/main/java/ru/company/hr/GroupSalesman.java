package ru.company.hr;

import ru.company.hr.entity.Employee;

public class GroupSalesman implements Group{
	
	@Override
	public long calculateSalary(Employee employee, long basicRate, int experience) {
		int koef = 1;
		int maxBonus = 35;
		long bonus = Math.min(basicRate * maxBonus / 100 , basicRate * experience * koef / 100);
		long bonusForSub = calculateSalarySubordinatesRec(employee) * 3 / 1000 ;
		return basicRate + bonus + bonusForSub;
	}
	
	@Override
	public long calculateSalary(long basicRate, int experience) {
		int koef = 1;
		int maxBonus = 35;
		long bonus = Math.min(basicRate * maxBonus / 100 , basicRate * experience * koef / 100);
		return basicRate + bonus;
	}
	
	public long calculateSalarySubordinatesRec(Employee employee) {
		long salarySub = 0;
		for(Employee emp: employee.getSubordinates()) {
			salarySub += emp.getSalary();
			calculateSalarySubordinatesRec(emp);
		}
		return salarySub;
		
	}
}
