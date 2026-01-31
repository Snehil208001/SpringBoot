package com.snehil.module2.services;

import com.snehil.module2.dto.EmployeeDTO;
import com.snehil.module2.entities.EmployeeEntity;
import com.snehil.module2.exceptions.ResourceNotFoundException;
import com.snehil.module2.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils; // CORRECT IMPORT

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployedById(Long id) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));

        return employeeRepository.findById(id).map(employeeEntity ->  modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        boolean exists = isExistsByEmployeeId(employeeId);
        if (!exists) throw new ResourceNotFoundException("Employee not found with id: "+employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isExistsByEmployeeId(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = isExistsByEmployeeId(employeeId);
        if (!exists) return null;

        // FIXED: Use instance 'employeeRepository', not class 'EmployeeRepository'
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        updates.forEach((key, value) -> {
            // FIXED: Use 'key' instead of undefined 'field'
            // FIXED: Use standard ReflectionUtils.findField
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, key);
            if (fieldToBeUpdated != null) {
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
            }
        });

        // FIXED: Added 'return' keyword
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}