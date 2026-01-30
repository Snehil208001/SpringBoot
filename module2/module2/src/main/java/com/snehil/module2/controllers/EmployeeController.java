package com.snehil.module2.controllers;

import com.snehil.module2.dto.EmployeeDTO;
import com.snehil.module2.entities.EmployeeEntity;
import com.snehil.module2.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET by ID
    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    // GET all
    @GetMapping
    public List<EmployeeEntity> getAllEmployees(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String sortBy) {
        return employeeRepository.findAll();
    }

    // POST
    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee) {
        return employeeRepository.save(inputEmployee);
    }

    // PUT
    @PutMapping("/{employeeId}")
    public String updateEmployeeById(@PathVariable Long employeeId) {
        return "Hello from PUT " + employeeId;
    }
}
