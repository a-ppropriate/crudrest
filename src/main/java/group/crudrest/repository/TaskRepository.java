package group.crudrest.repository;

import java.util.List;

import group.crudrest.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByTitle(String title);
    List<Task> findByEmployeeId(Long employee_id);

	Task findById(long id);
}
