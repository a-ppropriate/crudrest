package group.crudrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import group.crudrest.repository.EmployeeRepository;
import group.crudrest.model.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudrestApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudrestApplication.class, args);
  }

  /*
   * private static final Logger log =
   * LoggerFactory.getLogger(CrudrestApplication.class);
   * 
   * @Bean
   * public CommandLineRunner demo(EmployeeRepository repository) {
   * return (args) -> {
   * // save a few employees
   * repository.save(new Employee("Jack", "weee","test@example.com"));
   * repository.save(new Employee("Chloe", "mos cow","test1@example.com"));
   * repository.save(new Employee("Kim", "paris","qwe@example.com"));
   * repository.save(new Employee("David", "basement","zzz@example.com"));
   * repository.save(new Employee("Michelle", "qwerty","mmm@example.com"));
   * 
   * // fetch all employees
   * log.info("Employees found with findAll():");
   * log.info("-------------------------------");
   * repository.findAll().forEach(employee -> {
   * log.info(employee.toString());
   * });
   * log.info("");
   * 
   * // fetch an individual employee by ID
   * Employee employee = repository.findById(1L);
   * log.info("Employee found with findById(1L):");
   * log.info("--------------------------------");
   * log.info(employee.toString());
   * log.info("");
   * 
   * // fetch employees by last name
   * log.info("Employee found with findByName('Bauer'):");
   * log.info("--------------------------------------------");
   * repository.findByName("Chloe").forEach(chloe -> {
   * log.info(chloe.toString());
   * });
   * log.info("");
   * };
   * }
   */

}
