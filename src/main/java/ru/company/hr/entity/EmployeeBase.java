package ru.company.hr.entity;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.company.hr.EmployeeEnum;
import ru.company.hr.EmployeeService;
import ru.company.hr.IAbstractEmployeeService;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EmployeeBase {
	
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
    @Column(name = "GROUP_EMP")
	private EmployeeEnum group;
	
    @Column(name = "BASIC_RATE")
    @Min(value=0, message = "the base rate cannot be negative")
	private BigDecimal basicRate;
    
    @JdbcTypeCode(SqlTypes.BIGINT)
    @ManyToOne(cascade={CascadeType.ALL}, targetEntity = EmployeeBase.class)
	@JoinColumn(name="supervisor_id")
	private EmployeeBase supervisor;
    
    @OneToMany(mappedBy="supervisor", cascade=CascadeType.ALL)
    private List<EmployeeBase> subordinates = new ArrayList<EmployeeBase>();
	
    public EmployeeBase(String name, LocalDate dateofEmployment, BigDecimal basicRate) {
    	this.name = name;
    	this.dateOfEmployment = dateofEmployment;
    	this.basicRate = basicRate;
    }
    
	public BigDecimal calculateSalary(EmployeeBase employee, LocalDate date) {
		IAbstractEmployeeService service = new EmployeeService();
		return service.calculateSalary(employee, date);
		
	}
}
