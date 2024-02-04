package group.crudrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import group.crudrest.exceptions.EmployeeNotFoundException;
import group.crudrest.model.Employee;
import group.crudrest.model.Task;
import group.crudrest.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EmployeeNotFoundException(id);
                });
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Employee newEmployee, Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setAddress(newEmployee.getAddress());
                    employee.setEmail(newEmployee.getEmail());

                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public List<Task> getTaskList(@PathVariable Long id) {
        return employeeRepository.findById(id).map(
                employee -> employee.getTasks())
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

}
