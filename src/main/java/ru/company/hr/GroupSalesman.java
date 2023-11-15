package ru.company.hr;

public class GroupSalesman implements Group{
	@Override
	public long calculateSalary(long basicRate, int experience) {
		int koef = 1;
		int maxBonus = 35;
		long bonus = Math.min(basicRate * maxBonus / 100 , basicRate * experience * koef / 100);
		return basicRate + bonus;
	}
}
