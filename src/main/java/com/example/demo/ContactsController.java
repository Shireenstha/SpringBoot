package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactsController {

    @GetMapping("/feedback")
    public String showFeedbackForm() {
        return "homme"; 
    }

    @PostMapping("/submit_feedback")
    public String submitFeedback(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String feedback,
            @RequestParam String date) {
        
      
        System.out.println("ID: " + id);
        System.out.println("Patient Name: " + name);
        System.out.println("Feedback: " + feedback);
        System.out.println("Date: " + date);

    
        return "result"; 
    }
}
