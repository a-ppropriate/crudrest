package group.crudrest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;
	private String address;

	@NotBlank(message = "Email is mandatory")
	// Regex is from some java spring boot validation example.
	@Pattern(regexp = "^[\\w!#$%&`*+/=?`{|}~^-]+(?:\\.[\\w!#$%&`*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Email seems to be invalid")
	private String email;

	protected Employee() {
	}

	public Employee(String name, String address, String email) {
		this.name = name;
		this.address = address;
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format(
				"Employee[id=%d, name='%s', address='%s', email='%s']",
				id, name, address, email);
	}

	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Task> tasks;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}
}
