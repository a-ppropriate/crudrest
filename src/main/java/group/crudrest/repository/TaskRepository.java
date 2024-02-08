package group.crudrest.repository;

import java.util.List;

import group.crudrest.model.Task;
import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByTitle(String title);

	Task findById(long id);
}