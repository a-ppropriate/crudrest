package group.crudrest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
    private String email;

	protected Employee() {}

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
}
