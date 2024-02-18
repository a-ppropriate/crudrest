package group.crudrest.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.crudrest.repository.EmployeeAssistsInTaskRepository;
import group.crudrest.repository.EmployeeRepository;
import group.crudrest.repository.TaskRepository;
import jakarta.annotation.PostConstruct;

@Component
public class StaticContextInitializer { // TODO: check/ask if this sht is safe to use in such way

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeAssistsInTaskRepository employeeAssistsInTaskRepository;

    @PostConstruct
    public void init() {
        MappingUtil.setMapper(modelMapper);
        EmployeeUtil.setEmployeeRepository(employeeRepository);
        TaskUtil.setTaskRepository(taskRepository);
        EmployeeAssistanceInTaskUtil.setEmployeeAssistsInTaskRepository(employeeAssistsInTaskRepository);
    }
}
