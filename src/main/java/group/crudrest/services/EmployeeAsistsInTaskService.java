/*
 * package group.crudrest.services;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * 
 * import group.crudrest.model.Employee;
 * import group.crudrest.model.EmployeeAssistsInTask;
 * import group.crudrest.model.Task;
 * import group.crudrest.repository.EmployeeAssistsInTaskRepository;
 * 
 * @Service
 * public class EmployeeAsistsInTaskService {
 * 
 * @Autowired
 * EmployeeService employeeService;
 * 
 * @Autowired
 * TaskService taskService;
 * 
 * @Autowired
 * EmployeeAssistsInTaskRepository employeeAssistsInTaskRepository;
 * 
 * public EmployeeAssistsInTask createEmployeeAsistsInTaskService(Long
 * employee_id, Long task_id) {
 * 
 * // TODO: check if employee can actually be an assistant
 * // TODO: tests
 * 
 * Employee emp = employeeService.getEmployeeById(employee_id);
 * Task task = taskService.getTaskById(task_id);
 * EmployeeAssistsInTask emp_assists_task = new EmployeeAssistsInTask(emp,
 * task);
 * return employeeAssistsInTaskRepository.save(emp_assists_task);
 * }
 * }
 */
