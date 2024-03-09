package com.managerdetails;


import com.to.employee.Employee;
import com.to.employee.EmployeeException;
import com.to.employee.EmployeeService;
import com.to.mailsystem.Mail;
import com.to.managerdetails.Manager;
import com.to.managerdetails.ManagerDetailsRepository;
import com.to.managerdetails.ManagerDetailsService;
import com.to.managerdetails.ManagerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.employee.EmployeeServiceTest.createEmployee;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ManagerDetailsServiceTest {

    @Mock
    EmployeeService employeeService;
    @Mock
    ManagerDetailsRepository managerDetailsRepository;
    @InjectMocks
    ManagerDetailsService managerDetailsService;
    @Test
    public void when_getManagerDetails_is_called_with_invalid_employee_id_throw_error(){
        when(employeeService.getEmployeeById(1)).thenThrow(EmployeeException.class);
        assertThrows(EmployeeException.class,()->managerDetailsService.getManagerDetails(1));
    }
    @Test
    public void when_getManagerDetails_is_called_with_valid_employee_id_and_invalid_manager_id_throw_error(){
        when(employeeService.getEmployeeById(1)).thenReturn(EmployeeDetails().get(0));
        when(managerDetailsRepository.findById(EmployeeDetails().get(0).getManagerId())).thenReturn(Optional.empty());
        assertThrows(ManagerException.class,()->managerDetailsService.getManagerDetails(1));
    }
    @Test
    public void when_getManagerDetails_is_called_with_valid_employee_id_it_should_return_manager_details(){
        when(employeeService.getEmployeeById(1)).thenReturn(EmployeeDetails().get(0));
        when(managerDetailsRepository.findById(EmployeeDetails().get(0).getManagerId())).thenReturn(Optional.of(ManagerDetails().get(0)));
        Manager managerDetails=managerDetailsService.getManagerDetails(1);
        assertEquals("madhan",managerDetails.getManagerName());
    }
    public List<Manager> ManagerDetails(){
        return List.of(
                new Manager(1,"madhan",25),
                new Manager(2,"nithin",22),
                new Manager(3,"sanjay",39)
        );
    }
    public List<Employee> EmployeeDetails(){
        return List.of(
                createEmployee(1,"saravanan","Ford","Software Engineer","9384196731","Sara@gmail.com",25000.00,1,1,List.of(new Mail(1,"Google","Commuication","Hi...."),new Mail(1,"OutLook","Security","security alert..."))),
                createEmployee(2,"praveen","Kpit","Testing","9875327654","prav@gmail.com",20000.00,1,2,List.of(new Mail(2,"Google","Job","Hi...."),new Mail(2,"OutLook","Training","Training date..."))),
                createEmployee(3,"keerthi","Lister","Software Engineer","99345321568","keerthi@gmail.com",25000.00,3,3,List.of(new Mail(3,"Google","Commuication","Hi...."),new Mail(3,"OutLook","Security","security alert...")))
        );
    }
}