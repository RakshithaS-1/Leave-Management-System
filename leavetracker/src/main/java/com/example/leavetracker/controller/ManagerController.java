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

    @GetMapping("/manager")
    public String showAllLeaves(Model model) {
        List<Leave> leaves = leaveRepository.findAll();
        model.addAttribute("leaves", leaves);
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
        }
        return "redirect:/manager";
    }
}
