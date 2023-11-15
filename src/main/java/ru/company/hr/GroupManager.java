package ru.company.hr;

public class GroupManager implements Group {
	@Override
	public long calculateSalary(long basicRate, int experience) {
		int koef = 5;
		int maxBonus = 40;
		long bonus = Math.min(basicRate * maxBonus / 100 , basicRate * experience * koef / 100);
		return basicRate + bonus;
	}
}
