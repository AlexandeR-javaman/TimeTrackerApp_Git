package com.example.timetrackerapp.controller;


import com.example.timetrackerapp.model.Employee;
import com.example.timetrackerapp.model.LogEntry;
import com.example.timetrackerapp.repository.EmployeeRepository;
import com.example.timetrackerapp.repository.LogEntryRepository;
import com.example.timetrackerapp.security.EmployeeDetailsService;
import com.example.timetrackerapp.service.LogEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final LogEntryService logEntryService;

    private final LogEntryRepository logEntryRepository;
    private final EmployeeRepository employeeRepository;

    public UserController(LogEntryService logEntryService, LogEntryRepository logEntryRepository, EmployeeDetailsService employeeDetailsService, EmployeeRepository employeeRepository) {
        this.logEntryService = logEntryService;
        this.logEntryRepository = logEntryRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/journal")
    public String showUserJournalPage(Model model, Principal principal) {
        int employeeId = employeeRepository.findByLogin(principal.getName()).orElse(null).getId();
        model.addAttribute("logEntryList", logEntryRepository.findByEmployeeId(employeeId));
        return "userJournal";
    }

    @GetMapping("/start")
    public String showUserStartTimePage() {
        return "userStartTime";
    }

    @PostMapping("/start")
    public String userStartTime(Model model, Principal principal) {
        int employeeId = employeeRepository.findByLogin(principal.getName()).orElse(null).getId();
        System.out.println(employeeId);
        Optional<LogEntry> logEntry = logEntryRepository.findByEmployeeIdAndEndTime(employeeId, null);
        if (logEntry.isPresent()) {
            model.addAttribute("errorMessage", "Сначала закройте предыдущую смену");
            return "userStartTime";
        }
        logEntryService.userStartTime(employeeId);
        return "redirect:/user/journal";
    }

    @GetMapping("/end")
    public String showUserEndTimePage(Model model) {
        model.addAttribute("logEntry", new LogEntry());
        return "userEndTime";
    }

    @PostMapping("/end")
    public String userEndTime(@ModelAttribute LogEntry logEntityWithMessage, Model model, Principal principal) {
        int employeeId = employeeRepository.findByLogin(principal.getName()).orElse(null).getId();
        Optional<LogEntry> logEntry = logEntryRepository.findByEmployeeIdAndEndTime(employeeId, null);
        if (logEntry.isPresent()) {
            logEntryService.userStopTime(logEntityWithMessage, employeeId);
            return "redirect:/user/journal";
        }
        model.addAttribute("errorMessage", "Сначала начните смену");
        return "userEndTime";
    }
}
