package com.example.leavetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leaves")
public class Leave {
    
    @Id
    public String leaveId;
    public String employeeId;
    public String startDate;
    public int Days;
    public String reason;
    public String status = "PENDING";

    public String getId() {
        return leaveId;
    }

    public void setId(String leaveId) {
        this.leaveId = leaveId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return Days;
    }

    public void setDays(int days) {
        this.Days = days;
    }
 
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Leave{" +
                "id='" + leaveId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", startDate=" + startDate +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", Days=" + Days +
                '}';
    }
}
