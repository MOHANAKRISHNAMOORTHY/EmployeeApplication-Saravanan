package com.to.employee;

import com.to.employee.models.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/employee")
public class  EmployeeController {

    @Autowired     //Field injection
    private EmployeeService employeeService;

    private EmployeeJpaRepository employeeJpaRepository;   //Setter injection
    @Autowired
    public void setEmployeeJpaRepository(EmployeeJpaRepository employeeJpaRepository) {
        this.employeeJpaRepository = employeeJpaRepository;
    }

    @GetMapping("/message")
    public String message(){
        return "Welcome to My Employee App?" ;
    }

    //@pathvariable   https:xyz.com/update/5
    //@requestparam   https:xyz.com/update?id=5

    @GetMapping("/getemployee/id/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        try{
            return employeeService.getEmployeeById(id);
        }
        catch (EmployeeException employeeException){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,employeeException.getMessage());
        }
    }
    @PostMapping("/addemployee")
    public Employee addEmployee( @RequestBody EmployeeRequest employeeRequest){

        return employeeService.addEmployee(employeeRequest);
    }
    @PutMapping("/updateemployee")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }
    @DeleteMapping("/deleteemployee")                     //@RequestParam( what name in url for this variable ="id", required=false)
    public String deleteEmployeeById(@RequestParam Integer id){

        return employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/findAllEmployeeByName/name/{name}")
    public List<Employee> findAllEmployeeByName(@PathVariable String name){
        return employeeJpaRepository.findEmployeeByName(name);
    }
    @GetMapping("/findAllEmployeeBySalaryAmount/salaryAmount/{salaryAmount}")
    public List<Employee> findAllEmployeeBySalaryAmount(@PathVariable Double salaryAmount){
        return employeeJpaRepository.findEmployeeBySalaryAmount(salaryAmount);
    }
    @GetMapping("/findAllEmployee")
    public List<Employee> findAllEmployee(){
        return employeeJpaRepository.findAllEmployee();
    }
    @GetMapping("/findAllEmployeeAverageSalaryAmount")
    public Double findAllEmployeeAverageSalaryAmount(){
        return employeeJpaRepository.findAllEmployeeAverageSalaryAmount();
    }
    @GetMapping("/findAllEmployeeByJobRole/jobRole/{jobRole}")
    public List<Employee> findAllEmployeeByJobRole(@PathVariable String jobRole){
        return employeeJpaRepository.findAllEmployeeByJobRole(jobRole);
    }
    @GetMapping("/findAllEmployeeByJobRoleWithInnerQuery/jobRole/{jobRole}")
    public List<Employee> findAllEmployeeByJobRoleWithInnerQuery(@PathVariable String jobRole){
        return employeeJpaRepository.findAllEmployeeByJobRoleWithInnerQuery(jobRole);
    }
    @GetMapping("/findEmployeeByBetweenSalaryAmount")
    public List<Employee> findEmployeeByBetweenSalaryAmount(@RequestParam("minSalaryAmount") Double minAmount,@RequestParam("maxSalaryAmount") Double maxAmount){
        return employeeJpaRepository.findEmployeeByBetweenSalaryAmount(minAmount,maxAmount);
    }
    @GetMapping("/findEmployeeSalaryAverageByStreams")
    public Optional<Double> findEmployeeSalaryAverage(){
        return employeeService.findEmployeeSalaryAverage();
    }
}
