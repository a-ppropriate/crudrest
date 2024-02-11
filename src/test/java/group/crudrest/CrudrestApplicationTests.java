package group.crudrest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import group.crudrest.dto.TaskDTO;

import group.crudrest.model.Employee;
import group.crudrest.model.Task;
import group.crudrest.repository.EmployeeRepository;
import group.crudrest.services.EmployeeService;

@SpringBootTest
class CrudrestApplicationTests {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeService employeeService;

	@Test
	void contextLoads() {
	}

	@Test
	public void task2taskDTO() {
		Task task = new Task("title", "description");

		Employee employee = employeeService.getEmployeeById(1L);

		task.setEmployee(employee);

		TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);

		assertEquals(taskDTO.getTitle(), task.getTitle());
		assertEquals(taskDTO.getDescription(), task.getDescription());
		assertEquals(employee.getId(), task.getEmployee_id());
		assertEquals(taskDTO.getEmployee_id(), employee.getId());
		assertEquals(task.getEmployee_id(), employee.getId());

	}

	@Test
	public void taskDTO2task() {
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTitle("title");
		taskDTO.setDescription("description");
		taskDTO.setEmployee_id(1L);

		Employee employee = employeeService.getEmployeeById(1L);

		Task task = modelMapper.map(taskDTO, Task.class);

		assertEquals(taskDTO.getTitle(), task.getTitle());
		assertEquals(taskDTO.getDescription(), task.getDescription());
		assertEquals(employee.getId(), task.getEmployee_id());
		assertEquals(taskDTO.getEmployee_id(), employee.getId());
		assertEquals(task.getEmployee_id(), employee.getId());

	}

}
