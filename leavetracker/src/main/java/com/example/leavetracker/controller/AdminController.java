package com.example.leavetracker.controller;

import com.example.leavetracker.model.Employee;
import com.example.leavetracker.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("employee", new Employee());
        return "admin";
    }

    List<Command> commandHistory = new ArrayList<Command>();
    @PostMapping("/admin/addEmployee")
    public String addEmployee(Employee employee) {
    	Command command = new AddEmployeeCommand(employee);
        command.execute(employeeRepository);
        commandHistory.add(command);
        return "redirect:/admin";
    }

    public interface Command {
        void execute(EmployeeRepository employeeRepository);
    }

    public class AddEmployeeCommand implements Command {

        private final Employee employee;
    
        public AddEmployeeCommand(Employee employee) {
            this.employee = employee;
        }
    
        @Override
        public void execute(EmployeeRepository employeeRepository) {
            employee.setleaveLeft(10);
            employeeRepository.save(employee);
        }
    }
    

}
