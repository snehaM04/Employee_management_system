package com.employee.employeeManagement.Controller;

import com.employee.employeeManagement.Entity.Employee;
import com.employee.employeeManagement.Service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
     @Autowired
    private EmployeeService employeeService;
    @PostMapping("/employee")
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee)
    {
        Employee createEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createEmployee);
    }
    @GetMapping("/getEmployees")
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployee();
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id)
    {
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee id: " + id + " deleted successfully", HttpStatus.OK);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
    }
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id)
    {
        Employee getEmployee = employeeService.getEmployeeById(id);
        if(getEmployee == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getEmployee);
    }
    @PatchMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee)
    {
        Employee updatedEmployee = employeeService.updateEmployee(id,employee);
        if(updatedEmployee == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return ResponseEntity.ok(updatedEmployee);
    }

}

