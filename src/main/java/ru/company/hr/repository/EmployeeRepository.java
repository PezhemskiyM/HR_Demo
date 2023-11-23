package ru.company.hr.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.company.hr.entity.EmployeeBase;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeBase, Long>{
   // public List<MenuItem> findByNameContaining(String title);
    
    //public List<MenuItem> findByCostGreaterThan(int s);
    
    //public List<MenuItem> findByNameLikeAndCostGreaterThan(String title,int s);
    public List<EmployeeBase> findByNameContaining(String name);
    
    //public Employee findById(long id);
    
    /*@Query("select e.name from Employee as e where e.supervisor<=:supervisor")
    public List<EmployeeBase> getSubordinates(@Param("supervisor") EmployeeBase supervisor);*/
    
    // @Query("select b from MenuItem as b where b.cost<=:cost")
    //public List<MenuItem> getChipItem(@Param("cost") Integer cost);
}
