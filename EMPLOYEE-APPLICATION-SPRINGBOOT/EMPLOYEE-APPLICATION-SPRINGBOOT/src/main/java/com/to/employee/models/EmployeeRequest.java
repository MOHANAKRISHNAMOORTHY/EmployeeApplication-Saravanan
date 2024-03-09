package com.to.employee.models;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {
    private String name;
    private String companyName;
    private String jobRole;
    private String phoneNumber;
    private String emailId;
}
