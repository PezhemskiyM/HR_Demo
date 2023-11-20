package ru.company.hr;

import ru.company.hr.entity.Employee;

public class GroupEmployee implements Group {
	@Override
	public long calculateSalary(Employee employee, long basicRate, int experience) {
		int koef = 3;
		int maxBonus = 30;
		long bonus = Math.min(basicRate * maxBonus / 100 , basicRate * experience * koef / 100);
		return basicRate + bonus;
	}
	
	@Override
	public long calculateSalary(long basicRate, int experience) {
		int koef = 3;
		int maxBonus = 30;
		long bonus = Math.min(basicRate * maxBonus / 100 , basicRate * experience * koef / 100);
		return basicRate + bonus;
	}
}
