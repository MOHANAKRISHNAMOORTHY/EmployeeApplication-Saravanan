package com.to.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface EmployeeJpaRepository  extends JpaRepository<Employee,Integer> {

    //JPA Query
    List<Employee> findEmployeeByName(String name);
    List<Employee> findEmployeeBySalaryAmount(Double salaryAmount);
    List<Employee> findEmployeesByJobRoleIn(List<String> jobRole);
    //Custom Query
    @Query("SELECT employee FROM Employee employee")
    List<Employee> findAllEmployee();
    @Query("SELECT AVG(employee.salaryAmount) FROM Employee employee")
    Double findAllEmployeeAverageSalaryAmount();
    @Query("SELECT employee FROM Employee employee WHERE employee.jobRole = ?1")   // ?1 = argument number in parameter
    List<Employee> findAllEmployeeByJobRole(String jobRole);

//    @Query("SELECT employee FROM Employee employee WHERE employee.jobRole = :JOBROLE")
//    List<Employee> findAllEmployeeByJobRole(@Param("JOBROLE") String jobRole);

    @Query("SELECT employee FROM Employee employee WHERE employee.salaryAmount>= :minimumSalaryAmount AND employee.salaryAmount<=:maximumSalaryAmount")
    List<Employee> findEmployeeByBetweenSalaryAmount(@Param("minimumSalaryAmount") Double minAmount,
            @Param("maximumSalaryAmount") Double maxAmount);

//    @Query("SELECT employee FROM Employee employee WHERE employee.salaryAmount>= ?1 " +
//            "AND employee.salaryAmount<=?2")
//    List<Employee> findEmployeeByBetweenSalaryAmount(Double minAmount,Double maxAmount);

    @Query(value="SELECT * FROM Employee  WHERE id" +
            " IN (SELECT id FROM Employee  WHERE JOB_ROLE= :jobRole)",nativeQuery = true)
    List<Employee> findAllEmployeeByJobRoleWithInnerQuery(String jobRole);

    //native Query
    @Query(value="SELECT * FROM Employee ",nativeQuery = true)
    List<Employee> fullRecords();


}
