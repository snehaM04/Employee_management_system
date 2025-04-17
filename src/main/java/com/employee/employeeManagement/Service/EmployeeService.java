package com.employee.employeeManagement.Service;

import com.employee.employeeManagement.Entity.Employee;
import com.employee.employeeManagement.Repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployee()
    {
        return employeeRepository.findAll();
    }
    public void deleteEmployee(Long id)
    {
      if(!employeeRepository.existsById(id))
      {
          throw new EntityNotFoundException("Employee id:" + id + " Not found");
      }
      employeeRepository.deleteById(id);
    }
    public Employee getEmployeeById(Long id)
    {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id,Employee employee)
    {
       Optional<Employee> optionalEmployee = employeeRepository.findById(id);
       if(optionalEmployee.isPresent())
       {
         Employee existingEmployee = optionalEmployee.get();
         existingEmployee.setName(employee.getName());
         existingEmployee.setEmail(employee.getEmail());
         existingEmployee.setPhone(employee.getPhone());
         existingEmployee.setDepartment(employee.getDepartment());
         return employeeRepository.save(existingEmployee);
       }

        return null;
    }
}
