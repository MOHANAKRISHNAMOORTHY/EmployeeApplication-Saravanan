package com.to.employee;

import com.to.mailsystem.Mail;
import com.to.meetingdetails.Meeting;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Name of can't be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z]{3,16}", message = "Name of should contains min 3 & max 16 chars , no digits and special chars allowed.")
    @Valid
    private String name;
    private String companyName;
    private String jobRole;
    @Pattern(regexp = "[0-9]{10}",message = "Phone Number should contains only 10 digits")
    private String phoneNumber;
    @Email(message = "Please provide valid email. e.g name@ford.com")
    private String emailId;
    private Double salaryAmount;
    private Integer managerId;
    private Integer mailId;
    @OneToMany(mappedBy = "employee")
    private List<Mail> mailDetails;
    @ManyToMany
    @JoinColumn(name="meetingId")
    private List<Meeting> meetingDetails;

}
