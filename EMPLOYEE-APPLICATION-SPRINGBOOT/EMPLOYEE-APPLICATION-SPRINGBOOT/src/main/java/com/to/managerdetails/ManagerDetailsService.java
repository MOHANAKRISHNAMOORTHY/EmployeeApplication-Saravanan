package com.to.managerdetails;

import com.to.employee.Employee;
import com.to.employee.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerDetailsService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ManagerDetailsRepository managerDetailsRepository;
    public Manager getManagerDetails(int id){

        Employee employee=employeeService.getEmployeeById(id);
        return managerDetailsRepository.findById(employee.getManagerId())
                .orElseThrow(()->new ManagerException("Manager Id is not present"));

    }
}
