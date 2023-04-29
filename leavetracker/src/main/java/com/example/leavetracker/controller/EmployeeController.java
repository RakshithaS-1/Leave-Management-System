package com.example.leavetracker.controller;

import com.example.leavetracker.model.Employee;
import com.example.leavetracker.model.Leave;
import com.example.leavetracker.repository.EmployeeRepository;
import com.example.leavetracker.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;


import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @GetMapping("/employee")
    public String employee(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee";
    }

    @GetMapping("/employee/{employeeId}")
    public String employeeHome(@PathVariable String employeeId, Model model) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        model.addAttribute("employee", employee);
        System.out.println(employee);
        List<Leave> leaves = leaveRepository.findByEmployeeId(employeeId);
        model.addAttribute("leaves", leaves);
        model.addAttribute("leave", new Leave());
        return "employee_home";
    }

   
    @PostMapping("/employee/{employeeId}/applyLeave")
    public String applyLeave(@PathVariable String employeeId,@Valid Leave leave, BindingResult result, Model model) {
    	Employee employee = employeeRepository.findById(employeeId).orElse(null);
        int leaveLeft=employee.getleaveLeft();
        System.out.println("leaveLeft");
        System.out.println(leaveLeft);
        int requestedLeaves=leave.getDays();
        System.out.println("requestedLeaves");
        System.out.println(requestedLeaves);

        if(leaveLeft>= requestedLeaves) {
            leave.setEmployeeId(employeeId);
            leaveRepository.save(leave);
            return "redirect:/employee/" + employeeId; 
        }
        
        else {
        	return "redirect:/reachedlimit";
        }
    }

    @GetMapping("/reachedlimit")
    public String Recahedleavelimit() {
        return "reachedlimit";
    }
}
