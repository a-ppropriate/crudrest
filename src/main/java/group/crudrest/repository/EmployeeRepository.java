package group.crudrest.repository;

import java.util.List;

import group.crudrest.model.Employee;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByName(String name);

	Employee findById(long id);
}
