package ru.company.hr.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.company.hr.entity.EmployeeBase;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeBase, Long>{

    public List<EmployeeBase> findBySupervisor(EmployeeBase emp);
}
