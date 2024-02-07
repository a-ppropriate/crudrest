package group.crudrest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    private String address;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[\\w!#$%&`*+/=?`{|}~^-]+(?:\\.[\\w!#$%&`*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Email seems to be invalid")
    private String email;

    @Override
    public String toString() {
        return String.format(
                "Employee[id=%d, name='%s', address='%s', email='%s']",
                id, name, address, email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
