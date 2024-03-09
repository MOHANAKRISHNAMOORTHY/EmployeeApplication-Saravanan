package com.employee;

import com.to.employee.EmployeeController;
import com.to.employee.EmployeeJpaRepository;
import com.to.employee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    public EmployeeService employeeService;
    @Mock
    public EmployeeJpaRepository employeeJpaRepository;
    @InjectMocks
    public EmployeeController employeeController;

    @Test
    public void when_getEmployeeById_is_called_it_should_call_getEmployeeById_in_employeeService(){
        employeeController.getEmployeeById(1);
        Mockito.verify(employeeService).getEmployeeById(1);
    }
    @Test
    public void when_getEmployeeById_is_called_it_should_return_how_much_time_call_getEmployeeById_in_employeeService(){
        employeeController.getEmployeeById(1);
        Mockito.verify(employeeService,Mockito.times(1)).getEmployeeById(1);
    }
    @Test
    public void when_getEmployeeById_is_called_it_should_return_how_much_time_call_getEmployeeById_in_employeeService_by_atLeastOnce(){
        employeeController.getEmployeeById(1);
        Mockito.verify(employeeService,Mockito.atLeastOnce()).getEmployeeById(1);
        Mockito.verify(employeeService,Mockito.atLeast(1)).getEmployeeById(1);
    }
    @Test
    public void when_getEmployeeById_is_called_it_should_return_how_much_time_call_getEmployeeById_in_employeeService_by_atMostOnce(){
        employeeController.getEmployeeById(1);
        Mockito.verify(employeeService,Mockito.atMostOnce()).getEmployeeById(1);
        Mockito.verify(employeeService,Mockito.atMost(1)).getEmployeeById(1);
    }
}