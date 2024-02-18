package group.crudrest.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.Converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import group.crudrest.model.Employee;
import group.crudrest.model.Task;
import group.crudrest.utils.EmployeeUtil;

@Configuration
public class MapperConfig {

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        taskDTO2task(mapper);
        task2taskDTO(mapper);
        return mapper;
    }

    private void taskDTO2task(ModelMapper mapper) {
        TypeMap<TaskDTO, Task> propertyMapper = mapper.createTypeMap(TaskDTO.class, Task.class);

        propertyMapper
                .addMappings(mapping -> mapping.using(ID2Employee).map(TaskDTO::getEmployee_id, Task::setEmployee));
    }

    private void task2taskDTO(ModelMapper mapper) {

    }

    private final Converter<Long, Employee> ID2Employee = c -> EmployeeUtil.getEmployeeById(c.getSource());
}
