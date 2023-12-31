package ru.company.hr;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import ru.company.hr.entity.EmployeeBase;

public class EmployeeService implements IEmployeeService{

	private static final BigDecimal EMPLOYEE_PERCENT = new BigDecimal(0.03).setScale(2, RoundingMode.HALF_UP);
    private static final BigDecimal EMPLOYEE_MAX = new BigDecimal(0.3).setScale(2, RoundingMode.HALF_UP);

    private final BigDecimal MANAGER_PERCENT = new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP);
    private final BigDecimal MANAGER_MAX = new BigDecimal(0.4).setScale(2, RoundingMode.HALF_UP);
    private final BigDecimal MANAGER_PERCENT_SUBORDINATES = new BigDecimal(0.005).setScale(3, RoundingMode.HALF_EVEN);

    private final BigDecimal SALES_PERCENT = new BigDecimal(0.01).setScale(2, RoundingMode.HALF_UP);
    private final BigDecimal SALES_MAX = new BigDecimal(0.35).setScale(2, RoundingMode.HALF_UP);
    private final BigDecimal SALES_PERCENT_SUBORDINATES = new BigDecimal(0.003).setScale(3, RoundingMode.HALF_EVEN);

    private BigDecimal calculateSalaryWithPercent(EmployeeBase employee, LocalDate date) {

        BigDecimal percent = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.ZERO;

        if (employee.getGroup().equals(EmployeeEnum.EMPLOYEE)) {
            percent = EMPLOYEE_PERCENT;
            max = EMPLOYEE_MAX;
        } else if (employee.getGroup().equals(EmployeeEnum.MANAGER)) {
            percent = MANAGER_PERCENT;
            max = MANAGER_MAX;

        } else if (employee.getGroup().equals(EmployeeEnum.SALESMAN)) {
            percent = SALES_PERCENT;
            max = SALES_MAX;
        }


        int years = date.getYear() - employee.getDateOfEmployment().getYear();
        if (years < 0) {
            throw new ArithmeticException("The wage calculation date is shorter than the date of employment");
        }

        BigDecimal employeePercent = BigDecimal.valueOf(years).multiply(percent);

        if (employeePercent.compareTo(max) > 0) {
            employeePercent = max;
        }

        BigDecimal surcharge = employee.getBasicRate().multiply(employeePercent);

        return employee.getBasicRate().add(surcharge.setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public BigDecimal calculateSalary(EmployeeBase employee, LocalDate date) {
        BigDecimal salary = BigDecimal.ZERO;
        EmployeeEnum groupEmployee = employee.getGroup();
        
        BigDecimal baseSalary = calculateSalaryWithPercent(employee, date);
        
        if (groupEmployee.equals(EmployeeEnum.EMPLOYEE)) {
            salary = baseSalary;
        } else if (groupEmployee.equals(EmployeeEnum.MANAGER)) {
        	
        	BigDecimal salarySub = BigDecimal.ZERO;
            for (EmployeeBase sub : employee.getSubordinates()) {
            	salarySub = salarySub.add(calculateSalary(sub, date));
            }
            salarySub = salarySub.multiply(MANAGER_PERCENT_SUBORDINATES);
            salary = baseSalary.add(salarySub).setScale(2, RoundingMode.HALF_UP);
            
        } else if (groupEmployee.equals(EmployeeEnum.SALESMAN)) {

        	BigDecimal salarySub = BigDecimal.ZERO;
            salarySub = calculateSalaryAllSubordinates(employee.getSubordinates(), date);
            salarySub = salarySub.multiply(SALES_PERCENT_SUBORDINATES);

            salary = baseSalary.add(salarySub).setScale(2, RoundingMode.HALF_UP);
            
        }

        return salary;
    }

    private BigDecimal calculateSalaryAllSubordinates(List<EmployeeBase> listSub, LocalDate date) {
        BigDecimal salarySub = BigDecimal.ZERO;
    	for (EmployeeBase sub: listSub) {
        	
        	BigDecimal tempSalary = calculateSalary(sub, date);
        	salarySub = salarySub.add(tempSalary);
        	System.out.println("   " + tempSalary + "( " + sub.getName() + ")");
        	List<EmployeeBase> listSubSub = sub.getSubordinates();
        	if (listSubSub != null && listSubSub.size() > 0) {
                salarySub = salarySub.add(calculateSalaryAllSubordinates(listSubSub, date));
            }
        }
    	
        return salarySub;
    }
	
}
