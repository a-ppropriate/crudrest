package group.crudrest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import group.crudrest.dto.TaskDTO;
import group.crudrest.exceptions.UnexpectedBehaviourException;
import group.crudrest.model.Employee;
import group.crudrest.model.EmployeeAssistsInTask;
import group.crudrest.model.Task;
import group.crudrest.model.composite_keys.EmployeeAssistsInTaskKey;
import group.crudrest.repository.EmployeeAssistsInTaskRepository;
import group.crudrest.repository.EmployeeRepository;
import group.crudrest.repository.TaskRepository;
import group.crudrest.services.EmployeeService;
import group.crudrest.services.TaskService;

@SpringBootTest
class CrudrestApplicationTests {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TaskService taskService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeAssistsInTaskRepository employeeAssistsInTaskRepository;

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

	@Test
	public void TaskCascadeDeletion() {
		Employee employee = new Employee("a", "b", "ccc@cc.com");
		Employee created_employee = employeeService.createEmployee(employee);

		Task task = new Task("1", "1");
		task.setEmployee(created_employee);

		Task created_task = taskRepository.save(task);
		Long task_id = created_task.getId();

		if (task_id == null) {
			throw new UnexpectedBehaviourException("Something went horribly wrong");
		}

		employeeService.deleteEmployee(created_employee.getId());
		assertTrue(taskRepository.findById(task_id).isEmpty());
	}

	@Test
	public void AssistanceCascadeDeletionByEmployeeDeletion() {
		Employee owner_employee = new Employee("q", "w", "eee@cc.com");
		Employee created_owner_employee = employeeService.createEmployee(owner_employee);

		Employee employee = new Employee("a", "b", "ccc@cc.com");
		Employee created_employee = employeeService.createEmployee(employee);
		Long assistant_id = created_employee.getId();

		if (assistant_id == null) {
			throw new UnexpectedBehaviourException("Something went horribly wrong");
		}

		Task task = new Task("1", "1");
		task.setEmployee(created_owner_employee);

		Task created_task = taskRepository.save(task);
		Long task_id = created_task.getId();

		if (task_id == null) {
			throw new UnexpectedBehaviourException("Something went horribly wrong");
		}

		EmployeeAssistsInTask assist = new EmployeeAssistsInTask(created_employee, created_task);
		EmployeeAssistsInTask created_assist = employeeAssistsInTaskRepository.save(assist);
		EmployeeAssistsInTaskKey key = created_assist.getId();

		if (key == null) {
			throw new UnexpectedBehaviourException("Something went horribly wrong");
		}

		Optional<EmployeeAssistsInTask> res = employeeAssistsInTaskRepository.findById(key);

		assertTrue(created_assist.equals(res.get()));
		employeeService.deleteEmployee(assistant_id);

		Optional<EmployeeAssistsInTask> res1 = employeeAssistsInTaskRepository.findById(key);

		assertTrue(res1.isEmpty());
	}

	// TODO: move reusable code to helpers/methods
	@Test
	public void AssistanceCascadeDeletionByTaskDeletion() {
		Employee owner_employee = new Employee("q", "w", "eee@cc.com");
		Employee created_owner_employee = employeeService.createEmployee(owner_employee);

		Employee employee = new Employee("a", "b", "ccc@cc.com");
		Employee created_employee = employeeService.createEmployee(employee);
		Long assistant_id = created_employee.getId();

		if (assistant_id == null) {
			throw new UnexpectedBehaviourException("Something went horribly wrong");
		}

		Task task = new Task("1", "1");
		task.setEmployee(created_owner_employee);

		Task created_task = taskRepository.save(task);
		Long task_id = created_task.getId();

		if (task_id == null) {
			throw new UnexpectedBehaviourException("Something went horribly wrong");
		}

		EmployeeAssistsInTask assist = new EmployeeAssistsInTask(created_employee, created_task);
		EmployeeAssistsInTask created_assist = employeeAssistsInTaskRepository.save(assist);
		EmployeeAssistsInTaskKey key = created_assist.getId();

		if (key == null) {
			throw new UnexpectedBehaviourException("Something went horribly wrong");
		}

		Optional<EmployeeAssistsInTask> res = employeeAssistsInTaskRepository.findById(key);

		System.out.println(created_assist.toString());
		System.out.println(res.get().toString());

		assertTrue(created_assist.equals(res.get()));

		taskService.deleteTask(task_id);

		Optional<EmployeeAssistsInTask> res1 = employeeAssistsInTaskRepository.findById(key);

		assertTrue(res1.isEmpty());
	}

}
