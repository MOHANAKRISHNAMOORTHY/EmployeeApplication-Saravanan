package com.to.mailsystem;
import com.to.employee.Employee;
import com.to.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailSystemService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MailSystemRepository mailSystemRepository;
    public Mail getMailDetails(int id){

            Employee employee=employeeService.getEmployeeById(id);
            return mailSystemRepository.findById(employee.getMailId())
                    .orElseThrow(()->new MailException("Mail Id is not present"));
    }
}
