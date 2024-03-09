package com.mailsystem;

import com.to.employee.Employee;
import com.to.employee.EmployeeException;
import com.to.employee.EmployeeService;
import com.to.mailsystem.Mail;
import com.to.mailsystem.MailException;
import com.to.mailsystem.MailSystemRepository;
import com.to.mailsystem.MailSystemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.employee.EmployeeServiceTest.createEmployee;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MailSystemServiceTest {
    @Mock
    EmployeeService employeeService;
    @Mock
    MailSystemRepository mailSystemRepository;
    @InjectMocks
    MailSystemService mailSystemService;
    @Test
    public void when_getMailDetails_is_called_with_invalid_employee_id_throw_error(){
        when(employeeService.getEmployeeById(1)).thenThrow(EmployeeException.class);
        EmployeeException employeeException=assertThrows(EmployeeException.class,()->mailSystemService.getMailDetails(1));
        assertThat(employeeException.getMessage(),is("Employee Id is not present"));
    }
    @Test
    public void when_getMailDetails_is_called_with_valid_employee_id_and_invalid_mail_id_throw_error(){
        when(employeeService.getEmployeeById(1)).thenReturn(EmployeeDetails().get(0));
        when(mailSystemRepository.findById(EmployeeDetails().get(0).getMailId())).thenReturn(Optional.empty());
        MailException mailException=assertThrows(MailException.class,()->mailSystemService.getMailDetails(1));
        assertThat(mailException.getMessage(),is("Mail Id is not present"));
    }
    @Test
    public void when_getMailDetails_is_called_with_valid_employee_id_it_should_return_mail_details(){
        when(employeeService.getEmployeeById(1)).thenReturn(EmployeeDetails().get(0));
        when(mailSystemRepository.findById(EmployeeDetails().get(0).getMailId())).thenReturn(Optional.of(EmployeeDetails().get(0).getMailDetails().get(0)));
        Mail mailDetails=mailSystemService.getMailDetails(1);
        assertEquals("Commuication",mailDetails.getMailTitle());
    }
    public List<Employee> EmployeeDetails(){
        return List.of(
                createEmployee(1,"saravanan","Ford","Software Engineer","9384196731","Sara@gmail.com",25000.00,1,1,List.of(new Mail(1,"Google","Commuication","Hi...."),new Mail(1,"OutLook","Security","security alert..."))),
                createEmployee(2,"praveen","Kpit","Testing","9875327654","prav@gmail.com",20000.00,1,2,List.of(new Mail(2,"Google","Job","Hi...."),new Mail(2,"OutLook","Training","Training date..."))),
                createEmployee(3,"keerthi","Lister","Software Engineer","99345321568","keerthi@gmail.com",25000.00,3,3,List.of(new Mail(3,"Google","Commuication","Hi...."),new Mail(3,"OutLook","Security","security alert...")))
        );
    }

}