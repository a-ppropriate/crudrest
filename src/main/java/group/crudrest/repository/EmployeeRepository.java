package group.crudrest.repository;

import java.util.List;

import group.crudrest.model.Employee;
import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByName(String name);

	Employee findById(long id);
}
