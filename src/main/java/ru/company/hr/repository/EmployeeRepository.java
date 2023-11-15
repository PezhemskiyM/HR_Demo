package ru.company.hr.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.company.hr.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
   // public List<MenuItem> findByNameContaining(String title);
    
    //public List<MenuItem> findByCostGreaterThan(int s);
    
    //public List<MenuItem> findByNameLikeAndCostGreaterThan(String title,int s);
    public List<Employee> findByNameContaining(String name);
    
    //public Employee findById(long id);
    
    @Query("select e.name from Employee as e where e.supervisor<=:supervisor")
    public List<Employee> getSubordinates(@Param("supervisor") Employee supervisor);
    
    // @Query("select b from MenuItem as b where b.cost<=:cost")
    //public List<MenuItem> getChipItem(@Param("cost") Integer cost);
}
