package com.to.mailsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailSystemRepository extends JpaRepository<Mail,Integer> {
}
