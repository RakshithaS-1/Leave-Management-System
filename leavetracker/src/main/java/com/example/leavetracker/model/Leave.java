package com.example.leavetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "leaves")
public class Leave {

    @Id
    private String leaveId;
    private String employeeId;
    private String startDate;
    private String endDate;
    private String reason;
    private String employeeName;
    private String status="PENDING";

	/*
	 * public Leave() {}
	 * 
	 * public Leave(String employeeId, String startDate, String endDate, String
	 * reason) { this.employeeId = employeeId; this.startDate = startDate;
	 * this.endDate = endDate; this.reason = reason; this.status = "Pending"; }
	 */

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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
                ", endDate=" + endDate +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
