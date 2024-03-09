package com.to.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobRoleService {
    @Autowired
    private JobRoleJpaRepository jobRoleJpaRepository;
    public List<String> listOfJobRole(){
        return jobRoleJpaRepository.findEmployeesByJobRole();
    }
}
