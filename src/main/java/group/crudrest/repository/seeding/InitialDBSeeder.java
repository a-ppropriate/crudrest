package group.crudrest.repository.seeding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import group.crudrest.model.Task;
import group.crudrest.model.Employee;
import group.crudrest.model.EmployeeAssistsInTask;
import group.crudrest.repository.EmployeeAssistsInTaskRepository;
import group.crudrest.repository.EmployeeRepository;
import group.crudrest.repository.TaskRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class InitialDBSeeder {

    @Autowired
    private EmployeeAssistsInTaskRepository employeeAssistsInTaskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    private final List<String> names = Arrays.asList("John", "George", "Lucas", "Peter", "Fred");
    private final List<String> surnames = Arrays.asList("Johnes", "Smith", "Milton", "Anderson", "Weee");
    private final List<String> addresses = Arrays.asList("Paris", "Berlin", "London", "Moscow", "Rome");

    private final List<String> TLDs = Arrays.asList("com", "org", "net", "tw");
    private final List<String> SLDs = Arrays.asList("gmail", "mail", "microsoft", "somethingelse");

    private final List<String> task_names = Arrays.asList("laundry", "dishes", "trash", "world domination");

    private final Integer employee_amount = 10;
    private final Integer task_amount = 50;
    private final Integer assists_per_task = 7;

    private final <T> T get_random_from_list(List<T> list) {
        return list.get((int) (Math.random() * list.size()));
    }

    @PostConstruct
    public void initialSeeding() {

        if (employeeRepository.count() != 0 || taskRepository.count() != 0) {
            return;
        }

        ArrayList<Employee> employees = new ArrayList<Employee>(employee_amount);
        ArrayList<Task> tasks = new ArrayList<Task>(task_amount);

        for (int i = 0; i < employee_amount; i++) {
            String name = get_random_from_list(names);
            String surname = get_random_from_list(surnames);

            String address = get_random_from_list(addresses);
            String email = surname + i + "@" + get_random_from_list(SLDs) + "." + get_random_from_list(TLDs);

            Employee new_employee = employeeRepository.save(new Employee(name + " " + surname, address, email));
            employees.add(new_employee);
        }

        for (int i = 0; i < task_amount; i++) {
            String name = get_random_from_list(task_names) + " (" + i + ")";
            String description = "irrelevant";
            Employee employee = get_random_from_list(employees);
            Task task = new Task(name, description, employee);

            Task new_task = taskRepository.save(task);
            tasks.add(new_task);
        }

        if (employee_amount > 1) {

            for (int i = 0; i < task_amount; i++) {
                Task task = get_random_from_list(tasks);

                for (int j = 0; j < assists_per_task; j++) {
                    Employee assistant = get_random_from_list(employees);

                    while (assistant.getId() == task.getEmployee_id()) {
                        assistant = get_random_from_list(employees);
                    }

                    EmployeeAssistsInTask assist = new EmployeeAssistsInTask(assistant, task);
                    // TODO(?): check if the employee already assists with the task
                    // currently duplicates do not throw exceptions, so there will be none &
                    // less than expected assists overall
                    employeeAssistsInTaskRepository.save(assist);
                }

            }
        }
    }
}
