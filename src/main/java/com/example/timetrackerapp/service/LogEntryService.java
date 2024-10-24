package com.example.timetrackerapp.service;

import com.example.timetrackerapp.dto.LogEntryDto;
import com.example.timetrackerapp.model.Employee;
import com.example.timetrackerapp.model.LogEntry;
import com.example.timetrackerapp.repository.EmployeeRepository;
import com.example.timetrackerapp.repository.LogEntryRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogEntryService {
    private final LogEntryRepository logEntryRepository;
    private final EmployeeRepository employeeRepository;

    public LogEntryService(LogEntryRepository logEntryRepository, EmployeeRepository employeeRepository) {
        this.logEntryRepository = logEntryRepository;
        this.employeeRepository = employeeRepository;
    }

    public Iterable<LogEntry> findAll() {
        return logEntryRepository.findAll();
    }

    public void userStartTime(int employeeId) {
        LogEntry logEntry = new LogEntry();
        logEntry.setStartTime(LocalDateTime.now());
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        logEntry.setEmployee(employee);
        logEntryRepository.save(logEntry);
    }

    public void userStopTime(LogEntry logEntryWithMessage, int employeeId) {
        LogEntry logEntry = logEntryRepository.findByEmployeeIdAndEndTime(employeeId, null).orElse(null);
        logEntry.setMessage(logEntryWithMessage.getMessage());
        logEntry.setEndTime(LocalDateTime.now());
        logEntry.setJobTime(Duration.between(logEntry.getStartTime(), logEntry.getEndTime()).toHours());
        logEntryRepository.save(logEntry);
    }

    public List<LogEntryDto> findWithFilter(String surName, String employeePost,
                                             String stuffId, String jobTime) {
        List<LogEntryDto> resultList = logEntryRepository.findEntryStatistic();
        if (surName != null && !surName.isEmpty()) {
            resultList = resultList.stream().filter(f -> f.getSurname().equalsIgnoreCase(surName)).toList();
        }
        if (employeePost != null && !employeePost.isEmpty()) {
            resultList = resultList.stream().filter(f -> f.getEmployeePost().equalsIgnoreCase(employeePost)).toList();
        }
        if (stuffId != null && !stuffId.isEmpty()) {
            resultList = resultList.stream().filter(f -> f.getStuffId().equals(stuffId)).toList();
        }
        if (jobTime != null && !jobTime.isEmpty()) {
            resultList = resultList.stream().filter(f -> f.getSum().toString().equals(jobTime)).toList();
        }
        return resultList;
    }
}
