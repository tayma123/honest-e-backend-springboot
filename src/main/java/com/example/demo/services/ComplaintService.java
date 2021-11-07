package com.example.demo.services;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.entities.Complaint;
import com.example.demo.entities.Type;
import com.example.demo.repo.ComplaintRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ComplaintService {
    private final ComplaintRepo complaintRepo;
    private  final UserService userService;

    public ComplaintService(ComplaintRepo complaintRepo, UserService userService) {
        this.complaintRepo = complaintRepo;
        this.userService = userService;
    }


    public Complaint addComplaint(Complaint complaint) {

        return complaintRepo.save(complaint);
    }

    public List<Complaint> findAllComplaints() {
        return complaintRepo.findAll();
    }

    public Complaint updateComplaint(Complaint complaint) {
        return complaintRepo.save(complaint);
    }




    public void deleteComplaint(int  id) {
        complaintRepo.deleteComplaintById(id);

    }

    public Complaint findComplaintById(int id) {
        return
                complaintRepo.findComplaintById(id).orElseThrow(() -> new UserNotFoundException("complaint not found"));
    }
    public List<Complaint> findAllComplaintsByType(Type type) {
        List<Complaint> complaints = complaintRepo.findComplaintByType(type);
        return complaints;
    }

}


