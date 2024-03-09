package com.to.managerdetails;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Manager {
    @Id
    private Integer managerId;
    private String managerName;
    private Integer managerAge;

    public Manager(Integer managerId, String managerName, Integer managerAge) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.managerAge = managerAge;
    }

    public Manager() {

    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getManagerAge() {
        return managerAge;
    }

    public void setManagerAge(Integer managerAge) {
        this.managerAge = managerAge;
    }



}
