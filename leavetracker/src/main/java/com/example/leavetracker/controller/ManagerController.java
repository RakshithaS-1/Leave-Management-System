package com.example.leavetracker.controller;

import com.example.leavetracker.model.Leave;
import com.example.leavetracker.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.leavetracker.model.Employee;
import com.example.leavetracker.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ManagerController {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/manager")
    public String showAllLeaves(Model model) {
        List<Leave> leaves = leaveRepository.findAll();
        model.addAttribute("leaves", leaves);
        System.out.println(leaves);
        return "manager";
    }

    @GetMapping("/manager/approve/{leaveId}")
    public String validateLeave(@PathVariable String leaveId, Model model) {
        Optional<Leave> leaveOptional = leaveRepository.findById(leaveId);
        if (leaveOptional.isPresent()) {
            Leave leave = leaveOptional.get();
            model.addAttribute("leave", leave);
            return "validate_leave";
        } else {
            return "redirect:/manager";
        }
    }

    @PostMapping("/manager/approve/{leaveId}")
    public String processLeaveValidation(@PathVariable String leaveId, @RequestParam String status) {
        Optional<Leave> leaveOptional = leaveRepository.findById(leaveId);

        if (leaveOptional.isPresent()) {
            Leave leave = leaveOptional.get();
            leave.setStatus(status);

            leaveRepository.save(leave);

            if (status.equals("APPROVED")) {
                int requestedLeave = leave.getDays();
                System.out.println("leaveLeft");
                System.out.println(requestedLeave);
                String employeeId = leave.getEmployeeId();
                System.out.println("EmployeeID");
                System.out.println(employeeId);
                Employee employee = employeeRepository.findById(employeeId).orElse(null);
                int leaveLeft = employee.getleaveLeft();
                leaveLeft = leaveLeft - requestedLeave;
                employee.setleaveLeft(leaveLeft);
                employeeRepository.save(employee);
            }
        }
        return "redirect:/manager";
    }
}
