package com.example.demo.controlles;

import com.example.demo.entities.Complaint;
import com.example.demo.entities.Type;
import com.example.demo.entities.User;
import com.example.demo.services.ComplaintService;
import com.example.demo.services.EmailService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/complaint")

public class ComplaintController {
    private final ComplaintService complaintService;
    private final UserService userService;
    @Autowired
    private EmailService emailService;

    public ComplaintController(ComplaintService complaintService, UserService userService) {
        this.complaintService = complaintService;
        this.userService = userService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        List<Complaint> complaints = complaintService.findAllComplaints();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable("id") int id) {
        Complaint complaint = complaintService.findComplaintById(id);
        return new ResponseEntity<>(complaint, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
        Complaint newComplaint = complaintService.addComplaint(complaint);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("taymabenhmida1@gmail.com");
        mailMessage.setSubject(complaint.getObject());
        mailMessage.setFrom(complaint.getEmail());
        mailMessage.setText(complaint.getEmail());


        emailService.sendEmail(mailMessage);
        return new ResponseEntity<>(newComplaint, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/update")
    public ResponseEntity<Complaint> updateComplaint(@RequestBody Complaint complaint) {
        Complaint UpdateComplaint = complaintService.updateComplaint(complaint);
        return new ResponseEntity<>(UpdateComplaint, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComplaint(@PathVariable("id") int id) {
        complaintService.deleteComplaint(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findByUser/{email}")
    public ResponseEntity<Set<Complaint>> getComplaintByEmail(@PathVariable("email") String email) {
        User user = userService.findUserByEmail(email);
        Set<Complaint> ComplaintList = user.getComplaints();
        return new ResponseEntity<>(ComplaintList, HttpStatus.OK);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView) {

        modelAndView.setViewName("accountVerified");
        return modelAndView;
    }
    @GetMapping("/all/{type}")
    public ResponseEntity<List<Complaint>> getAllComplaintsByType(@PathVariable("type") Type type) {
        List<Complaint> complaints = complaintService.findAllComplaintsByType(type);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }
}
