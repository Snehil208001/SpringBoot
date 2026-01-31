package com.snehil.module2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snehil.module2.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3,max = 10,message = "Number of Characters in name should be in the range : {3,10")
    private String name;

    @NotBlank(message = "Email of the employee cannot be blank")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "Age of the employee cannot be blank")
    @Max(value = 80,message = "Age cannot be greater than 80")
    @Min(value = 18,message = "Age cannot be less than 18 ")
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
//@Pattern(regexp = "^(ADMIN|USER)$",message = "Role of Employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role; //ADMIN ,USER

    @NotNull(message = "Salary of employee should be not null")
    @Positive(message = "Salary of the employee should be positive")
    @Digits(integer = 6,fraction = 2,message = "The Salary can be in the form XXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "Date of joining field cannot be in the future")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    @Getter(AccessLevel.NONE)  // Prevent Lombok from generating isActive()
    private boolean isActive;

    // Manually define the getter
    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive") // <--- ADD THIS LINE. This merges the method with the field.
    public boolean isActive() {
        return isActive;
    }
}