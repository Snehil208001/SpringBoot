package com.snehil.module2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    @Getter(AccessLevel.NONE)  // Prevent Lombok from generating isActive()
    private boolean isActive;

    // Manually define the getter
    public boolean isActive() {
        return isActive;
    }
}