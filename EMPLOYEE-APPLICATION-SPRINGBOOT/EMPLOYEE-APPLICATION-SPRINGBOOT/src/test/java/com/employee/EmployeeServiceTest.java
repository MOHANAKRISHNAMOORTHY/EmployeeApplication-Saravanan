package com.employee;

import com.to.employee.models.EmployeeRequest;
import com.to.mailsystem.Mail;
import com.to.employee.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    public EmployeeJpaRepository employeeJpaRepository;
    @Spy
    public JobRoleService jobRoleService;
    @InjectMocks
    public EmployeeService employeeService;

    @Test
    public void when_getEmployeeById_is_called_it_should_call_findById_in_EmployeeJpaRepository(){
        when(employeeJpaRepository.findById(anyInt())).thenReturn(Optional.of(EmployeeDetails().get(0)));
        employeeService.getEmployeeById(1);
        Mockito.verify(employeeJpaRepository).findById(1);
    }

    @Test
    public void getEmploeeById_should_call_dinfById(){
        when(employeeJpaRepository.findById(anyInt())).thenReturn(Optional.of(EmployeeDetails().get(0)));
        employeeService.getEmployeeById(1);
        Mockito.verify(employeeJpaRepository.findById(1));
    }
    @Test
    public void when_getEmployeeById_is_called_with_invalid_id_it_should_return_null(){
        when(employeeJpaRepository.findById(anyInt())).thenReturn(Optional.of(EmployeeDetails().get(0)));
        employeeService.getEmployeeById(1);
        Mockito.verify(employeeJpaRepository).findById(1);
        when(employeeJpaRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(EmployeeException.class, ()->employeeService.getEmployeeById(2));
    }
    @Test
    public void when_findEmployeeSalaryAverage_is_called_it_should_return_average_salary_amount_of_allemployees(){
        employeeService.findEmployeeSalaryAverage();
        Mockito.verify(employeeJpaRepository).findAll();
        when(employeeJpaRepository.findAll()).thenReturn(EmployeeDetails());
        assertEquals(Optional.of(23750.00),employeeService.findEmployeeSalaryAverage());
    }
    public Employee createEmployee(){
        return Employee.builder().build();
    }
    public List<Employee> EmployeeDetails(){
        return List.of(
               createEmployee(1,"saravanan","Ford","Software Engineer","9384196731","Sara@gmail.com",25000.00,1,1,List.of(new Mail(1,"Google","Commuication","Hi...."),new Mail(1,"OutLook","Security","security alert..."))),
                createEmployee(2,"praveen","Kpit","Testing","9875327654","prav@gmail.com",20000.00,1,2,List.of(new Mail(2,"Google","Job","Hi...."),new Mail(2,"OutLook","Training","Training date..."))),
                createEmployee(3,"keerthi","Lister","Software Engineer","99345321568","keerthi@gmail.com",25000.00,3,3,List.of(new Mail(3,"Google","Commuication","Hi...."),new Mail(3,"OutLook","Security","security alert...")))
        );
    }

    public static Employee createEmployee(int id, String name, String companyName, String jobRole, String phoneNumber, String emailId, double salaryAmount, int managerId, int mailId, List<Mail> mailDetails) {
        return Employee.builder()
                .id(id)
                .name(name)
                .jobRole(jobRole)
                .companyName(companyName)
                .emailId(emailId)
                .phoneNumber(phoneNumber)
                .salaryAmount(salaryAmount)
                .managerId(managerId)
                .mailId(mailId)
                .mailDetails(mailDetails)
                .build();
    }

    @Test
    public void when_getEmployeeById_is_called_with_invalid_id_it_should_throw_employeeexception(){

        when(employeeJpaRepository.findById(anyInt())).thenReturn(Optional.empty());
        EmployeeException employeeException=assertThrows(EmployeeException.class, () -> employeeService.getEmployeeById(1));
        assertThat(employeeException.getMessage(),is("Employee Id is not present"));
        Mockito.verify(employeeJpaRepository).findById(any());
    }
//    @Test
//    public void when_findEmployeeByJobRole_is_called_it_should_return_list_of_employee_with_similar_jobrole(){
//        when(jobRoleService.listOfJobRole()).thenReturn(List.of("Software Engineer"));
//        when(employeeJpaRepository.findEmployeesByJobRoleIn(anyList())).thenReturn(List.of(EmployeeDetails().get(0),EmployeeDetails().get(2)));
//        List<Employee> employees=employeeService.findEmployeeByJobRole();
//        assertThat(employees.size(),greaterThan(0));
//    }
    @Test
    public void when_findEmployeeByJobRole_is_called_it_should_return_list_of_employee_with_similar_jobrole(){
        //when(jobRoleService.listOfJobRole()).thenReturn(List.of("Software Engineer"));
        doReturn(List.of("Software Engineer")).when(jobRoleService).listOfJobRole();
        when(employeeJpaRepository.findEmployeesByJobRoleIn(anyList())).thenReturn(List.of(EmployeeDetails().get(0),EmployeeDetails().get(2)));
        List<Employee> employees=employeeService.findEmployeeByJobRole();
        assertThat(employees.size(),greaterThan(0));
        Mockito.verify(employeeJpaRepository).findEmployeesByJobRoleIn(anyList());
    }
    @Test
    public void when_updateEmployee_is_called_it_should_return_the_updated_employee(){
        when(employeeJpaRepository.save(any())).thenReturn(EmployeeDetails().get(0));
        Employee employee=employeeService.updateEmployee(EmployeeDetails().get(0));
        assertEquals(employee.getCompanyName(),"Ford");
        Mockito.verify(employeeJpaRepository).save(any());
    }
    @Test
    public void when_deleteEmployeeById_is_called_it_should_return_the_deleted_message(){
        String employee=employeeService.deleteEmployeeById(1);
        assertEquals("Employee Successfully Deleted!",employee);
        Mockito.verify(employeeJpaRepository).deleteById(anyInt());
    }
    @Test
    public void when_addEmployee_is_called_it_should_return_the_added_employee(){
        when(employeeJpaRepository.save(any())).thenReturn(EmployeeDetails().get(0));
        Employee employee=employeeService.addEmployee(getMockEmployeeRequest());

        Mockito.verify(employeeJpaRepository).save(any());
    }

    private EmployeeRequest getMockEmployeeRequest() {
        return EmployeeRequest.builder()
                .name("saravanan")
                .companyName("ford")
                .jobRole("softwareEngineer")
                .emailId("sara127@ford.com")
                .phoneNumber("9384197394").build();
    }
}