package com.to.meetingdetails;

import com.to.employee.Employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Meeting {
    @Id
    private Integer meetingId;
    private String meetingName;
    private String meetingHost;
    @ManyToMany(mappedBy = "meetingDetails")
    private List<Employee> employee;

    public Meeting(Integer meetingId, String meetingName, String meetingHost, List<Employee> employee) {
        this.meetingId = meetingId;
        this.meetingName = meetingName;
        this.meetingHost = meetingHost;
        this.employee = employee;
    }

    public Meeting() {

    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingHost() {
        return meetingHost;
    }

    public void setMeetingHost(String meetingHost) {
        this.meetingHost = meetingHost;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
