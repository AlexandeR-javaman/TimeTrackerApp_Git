package com.example.timetrackerapp.repository;

import com.example.timetrackerapp.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository <Employee, Integer> {

    public Optional<Employee> findByLogin(String login);
}
