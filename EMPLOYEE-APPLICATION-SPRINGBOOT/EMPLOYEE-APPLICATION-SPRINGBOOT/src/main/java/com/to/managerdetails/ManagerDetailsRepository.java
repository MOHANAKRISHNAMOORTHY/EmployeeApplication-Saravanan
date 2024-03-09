package com.to.managerdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDetailsRepository extends JpaRepository<Manager,Integer> {
}
