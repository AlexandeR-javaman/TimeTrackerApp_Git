package com.example.timetrackerapp.repository;

import com.example.timetrackerapp.dto.LogEntryDto;
import com.example.timetrackerapp.model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LogEntryRepository extends JpaRepository <LogEntry, Integer> {

    public Optional<LogEntry> findByEmployeeIdAndEndTime(int employeeId, LocalDateTime endTime);

    public List<LogEntry> findByEmployeeId(int employeeId);

    @Query(value = "SELECT name, surname, patronymic, stuff_id, employee_post, count(job_time), sum(job_time)\n" +
            "FROM log_entry\n" +
            "JOIN employee on employee.id = log_entry.employee_id\n" +
            "GROUP BY name, surname, patronymic, stuff_id, employee_post", nativeQuery = true)
    public List<LogEntryDto> findEntryStatistic();
}
