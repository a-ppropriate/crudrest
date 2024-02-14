package group.crudrest.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import group.crudrest.exceptions.EmployeeAssistsInTaskNotFound;
import group.crudrest.exceptions.EmployeeIsAlreadyAnOwnerException;
import group.crudrest.exceptions.EmployeeNotFoundException;
import group.crudrest.model.Employee;
import group.crudrest.model.EmployeeAssistsInTask;
import group.crudrest.model.Task;
import group.crudrest.model.composite_keys.EmployeeAssistsInTaskKey;
import group.crudrest.repository.EmployeeAssistsInTaskRepository;
import group.crudrest.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    // @Autowired
    // EmployeeAsistsInTaskService employeeAsistsInTaskService;

    @Autowired
    EmployeeAssistsInTaskRepository employeeAssistsInTaskRepository;

    @Autowired
    TaskService taskService;

    public Optional<Employee> findEmployeeById(Long id) {
        Objects.requireNonNull(id);
        return employeeRepository.findById(id);
    }

    public Employee getEmployeeById(Long id) {
        return this.findEmployeeById(id).orElseThrow(
                () -> {
                    throw new EmployeeNotFoundException(id);
                });
    }

    public EmployeeAssistsInTask getAssistanceById(EmployeeAssistsInTaskKey key) {
        Objects.requireNonNull(key);
        return employeeAssistsInTaskRepository.findById(key).orElseThrow(
                () -> {
                    throw new EmployeeAssistsInTaskNotFound(key);
                });
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        return this.getEmployeeById(id);
    }

    public Employee createEmployee(Employee employee) {
        Objects.requireNonNull(employee);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Objects.requireNonNull(id);
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Employee newEmployee, Long id) {

        Employee emp = this.getEmployeeById(id);
        emp.setName(newEmployee.getName());
        emp.setAddress(newEmployee.getAddress());
        emp.setEmail(newEmployee.getEmail());
        return employeeRepository.save(emp);
    }

    public List<Task> getTaskList(@PathVariable Long id) {

        Employee emp = this.getEmployeeById(id);
        return emp.getTasks();
    }

    public List<Task> getAssistedTaskList(@PathVariable Long id) {

        Employee emp = this.getEmployeeById(id);
        return emp.getAssistedTasksRelations().stream().map(rel -> rel.getTask()).toList();
    }

    public Employee addAssistanceInTask(Long id, Long task_id) {
        Employee emp = this.getEmployeeById(id);
        Task task = taskService.getTaskById(task_id);

        if (id == task.getEmployee_id()) {
            throw new EmployeeIsAlreadyAnOwnerException(emp.getId(), task.getId());
        }

        EmployeeAssistsInTask emp_assists_task = new EmployeeAssistsInTask(emp, task);
        employeeAssistsInTaskRepository.save(emp_assists_task);
        return emp;
    }

    public void deleteAssistanceInTask(Long id, Long task_id) {
        EmployeeAssistsInTaskKey key = new EmployeeAssistsInTaskKey(id, task_id);
        EmployeeAssistsInTask emp_assists_task = this.getAssistanceById(key);
        Objects.requireNonNull(emp_assists_task);
        employeeAssistsInTaskRepository.delete(emp_assists_task);
    }
}
