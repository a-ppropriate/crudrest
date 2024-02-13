package group.crudrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import group.crudrest.model.EmployeeAssistsInTask;
import group.crudrest.model.composite_keys.EmployeeAssistsInTaskKey;

public interface EmployeeAssistsInTaskRepository
        extends JpaRepository<EmployeeAssistsInTask, EmployeeAssistsInTaskKey> {

}
