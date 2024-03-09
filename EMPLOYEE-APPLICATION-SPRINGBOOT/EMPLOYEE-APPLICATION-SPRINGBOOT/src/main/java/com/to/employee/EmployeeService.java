package com.to.employee;

import com.to.employee.models.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    //Map<Integer,Employee> employeeDtoMap = new HashMap<>();
    //    @Autowired
    //    private EmployeeJpaRepository employeeJpaRepository;
    private final JobRoleService jobRoleService;
    private final EmployeeJpaRepository employeeJpaRepository;     //constructor injection

    public EmployeeService(EmployeeJpaRepository employeeJpaRepository,JobRoleService jobRoleService) {
        this.employeeJpaRepository = employeeJpaRepository;
        this.jobRoleService=jobRoleService;
    }

    public Employee addEmployee(EmployeeRequest employeeRequest){
        Employee employee=createEmployeeFrom(employeeRequest);
        return employeeJpaRepository.save(employee);
    }

    private Employee createEmployeeFrom(EmployeeRequest employeeRequest) {
        return Employee.builder().name(employeeRequest.getName())
                .companyName(employeeRequest.getCompanyName())
                .jobRole(employeeRequest.getJobRole())
                .phoneNumber(employeeRequest.getPhoneNumber())
                .emailId(employeeRequest.getEmailId()).build();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeJpaRepository.findById(id).orElseThrow(()-> new EmployeeException("Employee Id is not present"));

//        Optional<Employee> employee=employeeJpaRepository.findById(id);
//        if(employee.isEmpty())
//            throw new EmployeeException("Employee Id is not present");
//        else
//            return employeeJpaRepository.findById(id).orElse(null); //.orElseThrow()   ;

    }

    public Employee updateEmployee(Employee employee) {

        return employeeJpaRepository.save(employee);
    }

    public String deleteEmployeeById(Integer id) {
        employeeJpaRepository.deleteById(id);
        return "Employee Successfully Deleted!";
    }

    public Optional<Double> findEmployeeSalaryAverage(){
        return employeeJpaRepository.findAll().stream().map(Employee::getSalaryAmount).       //map(index->index.getSalaryAmount())
                reduce((number1,number2)->(number1+number2)/2);
    }
    public List<Employee> findEmployeeByJobRole(){
        List<String> jobRole=jobRoleService.listOfJobRole();
        return employeeJpaRepository.findEmployeesByJobRoleIn(jobRole);
    }

}
