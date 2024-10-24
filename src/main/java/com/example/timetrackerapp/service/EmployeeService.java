package com.example.timetrackerapp.service;

import com.example.timetrackerapp.model.Employee;
import com.example.timetrackerapp.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Iterable<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole("ROLE_USER");
        employeeRepository.save(employee);
    }
}
