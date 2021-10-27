package univ.lille.gl.sra1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import univ.lille.gl.sra1.dao.EmployeeDao;
import univ.lille.gl.sra1.model.Employee;

public interface EmployeeRepository extends EmployeeDao, CrudRepository<Employee, Long>{
	
	@Query("SELECT e FROM Employee e ORDER BY e.id ASC")
	public List<Employee> findAllEmployees();
	
	public Employee findById(long id);
}
