package group.crudrest.utils;

import java.util.Objects;
import java.util.Optional;

import group.crudrest.exceptions.EmployeeNotFoundException;
import group.crudrest.model.Employee;
import group.crudrest.repository.EmployeeRepository;

public final class EmployeeUtil {
    private static EmployeeRepository employeeRepository;

    public static void setEmployeeRepository(EmployeeRepository employeeRepository) {
        EmployeeUtil.employeeRepository = employeeRepository;
    }

    private EmployeeUtil() {
    }

    public static Employee getEmployeeById(Long id) {
        Objects.requireNonNull(id);
        return EmployeeUtil.findEmployeeById(id).orElseThrow(() -> {
            throw new EmployeeNotFoundException(id);
        });
    }

    public static Optional<Employee> findEmployeeById(Long id) {
        Objects.requireNonNull(id);
        return employeeRepository.findById(id);
    }
}
