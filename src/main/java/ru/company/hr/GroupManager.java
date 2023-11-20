package ru.company.hr;

import ru.company.hr.entity.Employee;

public class GroupManager implements Group {
	@Override
	public long calculateSalary(Employee employee, long basicRate, int experience) {
		int koef = 5;
		int maxBonus = 40;
		long bonus = Math.min(basicRate * maxBonus / 100 , basicRate * experience * koef / 100);
		long bonusForSub = 0;
		for(Employee emp: employee.getSubordinates()) {
			bonusForSub += emp.getSalary();
		}
		bonusForSub = bonusForSub * 5 / 1000;
		return basicRate + bonus + bonusForSub;
	}
	
	@Override
	public long calculateSalary(long basicRate, int experience) {
		int koef = 5;
		int maxBonus = 40;
		long bonus = Math.min(basicRate * maxBonus / 100 , basicRate * experience * koef / 100);
		
		return basicRate + bonus;
	}
}
