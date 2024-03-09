package com.to.meetingdetails;

import com.to.employee.Employee;
import com.to.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingDetailsService {
    @Autowired
    private EmployeeService employeeService;
    public void getMeetingDetails(Integer id){

            Employee employee=employeeService.getEmployeeById(id);


    }
}
