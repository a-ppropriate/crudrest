package group.crudrest.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import group.crudrest.exceptions.EmployeeIsAlreadyAnOwnerException;
import group.crudrest.model.Employee;
import group.crudrest.model.EmployeeAssistsInTask;
import group.crudrest.model.Task;
import group.crudrest.model.composite_keys.EmployeeAssistsInTaskKey;
import group.crudrest.repository.EmployeeAssistsInTaskRepository;
import group.crudrest.repository.EmployeeRepository;
import group.crudrest.utils.EmployeeAssistanceInTaskUtil;
import group.crudrest.utils.EmployeeUtil;
import group.crudrest.utils.TaskUtil;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeAssistsInTaskRepository employeeAssistsInTaskRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {

        return EmployeeUtil.getEmployeeById(id);
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

        Employee emp = EmployeeUtil.getEmployeeById(id);
        emp.setName(newEmployee.getName());
        emp.setAddress(newEmployee.getAddress());
        emp.setEmail(newEmployee.getEmail());
        return employeeRepository.save(emp);
    }

    public List<Task> getTaskList(@PathVariable Long id) {

        Employee emp = EmployeeUtil.getEmployeeById(id);
        return emp.getTasks();
    }

    public List<Task> getAssistedTaskList(@PathVariable Long id) {

        Employee emp = EmployeeUtil.getEmployeeById(id);
        return emp.getAssistedTasksRelations().stream().map(rel -> rel.getTask()).toList();
    }

    public Employee addAssistanceInTask(Long id, Long task_id) {
        Employee emp = EmployeeUtil.getEmployeeById(id);
        Task task = TaskUtil.getTaskById(task_id);

        if (id == task.getEmployee().getId()) {
            throw new EmployeeIsAlreadyAnOwnerException(emp.getId(), task.getId());
        }

        EmployeeAssistsInTask emp_assists_task = new EmployeeAssistsInTask(emp, task);
        employeeAssistsInTaskRepository.save(emp_assists_task);
        return emp;
    }

    public void deleteAssistanceInTask(Long id, Long task_id) {
        EmployeeAssistsInTaskKey key = new EmployeeAssistsInTaskKey(id, task_id);
        EmployeeAssistsInTask emp_assists_task = EmployeeAssistanceInTaskUtil.getAssistanceById(key);
        Objects.requireNonNull(emp_assists_task);
        employeeAssistsInTaskRepository.delete(emp_assists_task);
    }
}
