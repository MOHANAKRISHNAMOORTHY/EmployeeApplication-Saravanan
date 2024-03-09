package com.to.mailsystem;

import com.to.employee.Employee;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

public class Mail {
    @Id
    private Integer mailId;
    private String mailType;
    private String mailTitle;
    private String mailDiscription;
    @ManyToOne
    @JoinColumn(name = "id")
    private Employee employee;

    public Mail(Integer mailId, String mailType, String mailTitle, String mailDiscription) {
        this.mailId = mailId;
        this.mailType = mailType;
        this.mailTitle = mailTitle;
        this.mailDiscription = mailDiscription;
    }

    public Mail(){

    }


    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getMailDiscription() {
        return mailDiscription;
    }

    public void setMailDiscription(String mailDiscription) {
        this.mailDiscription = mailDiscription;
    }
}

