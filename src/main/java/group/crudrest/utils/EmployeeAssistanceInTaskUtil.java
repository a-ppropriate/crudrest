package group.crudrest.utils;

import java.util.Objects;

import group.crudrest.exceptions.EmployeeAssistsInTaskNotFound;
import group.crudrest.model.EmployeeAssistsInTask;
import group.crudrest.model.composite_keys.EmployeeAssistsInTaskKey;
import group.crudrest.repository.EmployeeAssistsInTaskRepository;

public final class EmployeeAssistanceInTaskUtil {
    private static EmployeeAssistsInTaskRepository employeeAssistsInTaskRepository;

    private EmployeeAssistanceInTaskUtil() {
    }

    public static void setEmployeeAssistsInTaskRepository(
            EmployeeAssistsInTaskRepository employeeAssistsInTaskRepository) {
        EmployeeAssistanceInTaskUtil.employeeAssistsInTaskRepository = employeeAssistsInTaskRepository;
    }

    public static EmployeeAssistsInTask getAssistanceById(EmployeeAssistsInTaskKey key) {
        Objects.requireNonNull(key);
        return employeeAssistsInTaskRepository.findById(key).orElseThrow(
                () -> {
                    throw new EmployeeAssistsInTaskNotFound(key);
                });
    }

}
