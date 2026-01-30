package com.snehil.module2.controllers;

import com.snehil.module2.dto.EmployeeDTO;
import com.snehil.module2.entities.EmployeeEntity;
import com.snehil.module2.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET by ID
    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployedById(id);
    }

    // GET all
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String sortBy) {
        return employeeService.getAllEmployees();
    }

    // POST
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
        return employeeService.createNewEmployee(inputEmployee);
    }

    // PUT

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return employeeService.updateEmployeeById(employeeId, employeeDTO);
    }

    //Delete

    @DeleteMapping(path = "/{employeeId")
    public void deleteEmployeeById(@PathVariable Long employeeId){
        employeeService.eleteEmployeeById(employeeId);
    }

}
