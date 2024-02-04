package group.crudrest.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.Task;

import group.crudrest.model.Employee;
import group.crudrest.exceptions.*;
import group.crudrest.repository.*;

@Configuration
public class MapperConfig {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        configDTO2task(mapper);
        return mapper;
    }

    private void configDTO2task(ModelMapper mapper) {
        TypeMap<TaskDTO, Task> propertyMapper = mapper.createTypeMap(TaskDTO.class, Task.class);

        // propertyMapper.addMapping(src -> employeeRepository.findById(
        // src.getEmployee_id()), (dest, v) -> dest.setEmployee(v));

        // propertyMapper.addMapping(src -> employeeRepository.findById(
        // src.getEmployee_id()).orElseThrow(() -> {
        // throw new EmployeeNotFoundException(src.getEmployee_id());
        // }), (dest, v) -> dest.setEmployee(v));
    }
}
