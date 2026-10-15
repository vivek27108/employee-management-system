package com.employee.employee_management.service;

import com.employee.employee_management.entity.Employee;
import com.employee.employee_management.exception.EmployeeNotFoundException;
import com.employee.employee_management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException("Employee with id " + id + " not found"));
    }

    public Employee updateEmployee( Long id, Employee employee){
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException("Employee with id " + id + " not found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());
        return employeeRepository.save(existingEmployee);
    }

    public String deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id))
        {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }
}
