package com.ems.system.controllers;


import com.ems.system.entities.Employee;
import com.ems.system.exceptionhandler.ResourceNotFoundException;
import com.ems.system.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class EmployeesController {

    EmployeeRepository employeeRepository;

    @GetMapping("/get-all-employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/create-employee")
    public ResponseEntity<Employee> createEmployeeList(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok((employeeRepository.save(employee)));
    }

    @GetMapping("/get-employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with the id:" + id));
        return ResponseEntity.ok(employee);

    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with the id:" + id));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Successfully", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        Employee employeeUpdate = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with the id:" + id));
        employeeUpdate.setFirstName(employee.getFirstName());
        employeeUpdate.setLastName(employee.getLastName());
        employeeUpdate.setAge(employee.getAge());
        employeeUpdate.setCity(employee.getCity());
        employeeUpdate.setState(employee.getState());
        employeeUpdate.setSalary(employee.getSalary());
        employeeUpdate.setJoinAt(employee.getJoinAt());
        Employee updatedEmployee = employeeRepository.save(employeeUpdate);
        return ResponseEntity.ok(updatedEmployee);
    }
}
