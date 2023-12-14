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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.company.hr.EmployeeEnum;
import ru.company.hr.EmployeeService;
import ru.company.hr.IEmployeeService;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
@JsonSubTypes({
    @JsonSubTypes.Type(Employee.class),
    @JsonSubTypes.Type(Manager.class),
    @JsonSubTypes.Type(Salesman.class) 
    }
)
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
    
    @JsonIgnore
    @ManyToOne(cascade={CascadeType.MERGE}, targetEntity = EmployeeBase.class)
	@JoinColumn(name="supervisor_id")
	private EmployeeBase supervisor;
    
    @OneToMany(mappedBy="supervisor", cascade=CascadeType.MERGE)
    private List<EmployeeBase> subordinates = new ArrayList<EmployeeBase>();
	
    public EmployeeBase(String name, LocalDate dateofEmployment, BigDecimal basicRate) {
    	this.name = name;
    	this.dateOfEmployment = dateofEmployment;
    	this.basicRate = basicRate;
    }
    
    public EmployeeBase(String name, LocalDate dateofEmployment, BigDecimal basicRate, EmployeeEnum group) {
    	this.name = name;
    	this.dateOfEmployment = dateofEmployment;
    	this.basicRate = basicRate;
    	this.group = group;
    }
    
	public BigDecimal calculateSalary(EmployeeBase employee, LocalDate date) {
		IEmployeeService service = new EmployeeService();
		return service.calculateSalary(employee, date);
		
	}
}
