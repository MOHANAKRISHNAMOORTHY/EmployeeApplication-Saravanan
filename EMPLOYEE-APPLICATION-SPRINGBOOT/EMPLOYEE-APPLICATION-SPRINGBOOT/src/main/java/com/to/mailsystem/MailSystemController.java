package com.to.mailsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/mailsystem")
public class MailSystemController {
    @Autowired
    private MailSystemService mailSystemService;
    @GetMapping("/getMailDetails/id/{id}")
    public Mail getMailDetails(@PathVariable Integer id){
        return mailSystemService.getMailDetails(id);
    }
}
