package com.to.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobRoleJpaRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT employee FROM Employee employee Where employee.jobRole='Software Developer'")
    List<String> findEmployeesByJobRole();
}
