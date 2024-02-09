package group.crudrest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(name = "Employee", description = "Employee entity")
public class EmployeeDTO {
    @Schema(description = "Id", accessMode = AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Schema(description = "Name", example = "Bob")
    @Size(max = 100, message = "Employee name should be no longer than 100 characters")
    private String name;

    @Size(max = 100, message = "Employee address should be no longer than 100 characters")
    private String address;

    @NotBlank(message = "Email is mandatory")
    @Schema(description = "Email", example = "test@example.com")
    @Size(max = 100, message = "Employee email should be no longer than 100 characters")
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
