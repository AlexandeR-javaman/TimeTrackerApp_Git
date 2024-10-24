package com.example.timetrackerapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_Id", referencedColumnName = "id")
    private Employee employee;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String message;

    private Long jobTime;

    public LogEntry() {

    }

    public LogEntry(Employee employee, LocalDateTime startTime, LocalDateTime endTime, String message, Long jobTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.message = message;
        this.jobTime = jobTime;
    } // полный конструктор, тоже сделал, т.к. так надо

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getJobTime() {
        return jobTime;
    }

    public void setJobTime(Long jobTime) {
        this.jobTime = jobTime;
    }
}

