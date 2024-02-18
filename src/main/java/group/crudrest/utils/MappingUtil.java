package group.crudrest.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import group.crudrest.dto.EmployeeDTO;
import group.crudrest.dto.TaskDTO;
import group.crudrest.model.Employee;
import group.crudrest.model.Task;

public final class MappingUtil {
    private static ModelMapper modelMapper;

    private MappingUtil() {
    }

    public static void setMapper(ModelMapper modelMapper) {
        MappingUtil.modelMapper = modelMapper;
    }

    public static Employee mapEmployee(EmployeeDTO c) {
        Objects.requireNonNull(c);
        return modelMapper.map(c, Employee.class);
    }

    public static EmployeeDTO mapEmployee(Employee c) {
        Objects.requireNonNull(c);
        return modelMapper.map(c, EmployeeDTO.class);
    }

    public static Task mapTask(TaskDTO c) {
        Objects.requireNonNull(c);
        return modelMapper.map(c, Task.class);
    }

    public static TaskDTO mapTask(Task c) {
        Objects.requireNonNull(c);
        return modelMapper.map(c, TaskDTO.class);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static <S, T> Page<T> mapPage(Page<S> source, Class<T> targetClass) {
        return source.map(element -> modelMapper.map(element, targetClass));
    }

}
