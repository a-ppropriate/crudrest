package group.crudrest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import group.crudrest.dto.TaskDTO;
import group.crudrest.exceptions.UnexpectedBehaviourException;
import group.crudrest.model.Employee;
import group.crudrest.model.Task;
import group.crudrest.model.composite_keys.EmployeeAssistsInTaskKey;
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
	void modelmapper1() {
		// works as intended

		System.out.println("First test - 1");
		ModelMapper testMapper = new ModelMapper();
		TypeMap<TaskDTO, Task> propertyMapper = testMapper.createTypeMap(TaskDTO.class, Task.class);

		Converter<Long, Employee> ID2Employee = c -> employeeService.getEmployeeById(c.getSource());

		propertyMapper
				.addMappings(mapping -> mapping.using(ID2Employee).map(TaskDTO::getEmployee_id, Task::setEmployee));
		System.out.println("First test - 2");

		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTitle("title");
		taskDTO.setDescription("description");
		taskDTO.setEmployee_id(1L);

		Employee employee = employeeService.getEmployeeById(1L);

		Task task = testMapper.map(taskDTO, Task.class);

		assertEquals(taskDTO.getTitle(), task.getTitle());
		assertEquals(taskDTO.getDescription(), task.getDescription());
		assertEquals(employee.getId(), task.getEmployee_id());
		assertEquals(taskDTO.getEmployee_id(), employee.getId());
		assertEquals(task.getEmployee_id(), employee.getId());
		System.out.println("First test - 3");
	}

	@Test
	void modelmapper2() {
		// as shown in: https://www.baeldung.com/java-modelmapper p. 4.1
		// fails with exception group.crudrest.exceptions.EmployeeNotFoundException:
		// Could not find employee 0
		System.out.println("Second test - 1");

		ModelMapper testMapper = new ModelMapper();
		TypeMap<TaskDTO, Task> propertyMapper = testMapper.createTypeMap(TaskDTO.class, Task.class);

		propertyMapper.addMappings(
				mapper -> mapper.map(src -> {
					return employeeService.getEmployeeById(src.getEmployee_id());
				}, Task::setEmployee));

		System.out.println("Second test - 2");

		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setTitle("title");
		taskDTO.setDescription("description");
		taskDTO.setEmployee_id(1L);

		Employee employee = employeeService.getEmployeeById(1L);

		Task task = testMapper.map(taskDTO, Task.class);

		assertEquals(taskDTO.getTitle(), task.getTitle());
		assertEquals(taskDTO.getDescription(), task.getDescription());
		assertEquals(employee.getId(), task.getEmployee_id());
		assertEquals(taskDTO.getEmployee_id(), employee.getId());
		assertEquals(task.getEmployee_id(), employee.getId());
		System.out.println("Second test - 3");
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

	@Test
	public void EmployeeAssistKeyTest() {
		EmployeeAssistsInTaskKey k1 = new EmployeeAssistsInTaskKey(12L, 12L);
		EmployeeAssistsInTaskKey k2 = new EmployeeAssistsInTaskKey(12L, 12L);

		assertEquals(k1, k2);
	}

	@Test
	public void UnexpectedBehaviourOnTaskTest() {
		Task t = new Task("1", "1");

		assertThrows(UnexpectedBehaviourException.class, () -> t.getEmployee_id(),
				"A proper Task is not supposed to exist without employee: " + t.toString());
	}

}
