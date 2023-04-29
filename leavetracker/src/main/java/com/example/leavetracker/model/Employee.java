package com.example.leavetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

    @Id
    private String employeeId;
    private String name;
    private String email;
    private String designation;
    private int leaveLeft;


    public String getId() {
        return employeeId;
    }

    public void setId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public int getleaveLeft() {
        return leaveLeft;
    }

    public void setleaveLeft(int leaveLeft) {
        this.leaveLeft =leaveLeft;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                ", leaveLeft=" + leaveLeft +
                '}';
    }
}
