package com.example.timetrackerapp.controller;

import com.example.timetrackerapp.model.Employee;
import com.example.timetrackerapp.service.EmployeeService;
import com.example.timetrackerapp.service.LogEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final EmployeeService employeeService;

    private final LogEntryService logEntryService;

    public AdminController(EmployeeService employeeService, LogEntryService logEntryService) {
        this.employeeService = employeeService;
        this.logEntryService = logEntryService;
    }

    @GetMapping("/addEmployee")
    public String showAddEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "adminAddEmployee";
    }

    @PostMapping("/addEmployee")
    public String createNewEmployee(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/admin/stuff";
    }

    @GetMapping("/journal")
    public String showAdminJournalPage(Model model,
                                       @RequestParam(value = "surName", required = false) String surName,
                                       @RequestParam(value = "employeePost", required = false) String employeePost,
                                       @RequestParam(value = "stuffId", required = false) String stuffId,
                                       @RequestParam(value = "jobTime", required = false) String jobTime) {
        model.addAttribute("logEntryList", logEntryService.findWithFilter(surName, employeePost,
                stuffId, jobTime));
        return "adminJournal";
    }

    @GetMapping("/stuff")
    public String showAdminStuffPage(Model model) {
        model.addAttribute("employeeList", employeeService.findAll());
        return "adminStuff";
    }

    @GetMapping("/logEntry")
    public String showAdminLogEntryPage(Model model) {
        model.addAttribute("logEntryList", logEntryService.findAll());
        return "adminLogEntry";
    }

}
