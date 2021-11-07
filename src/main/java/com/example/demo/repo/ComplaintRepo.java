package com.example.demo.repo;

import com.example.demo.entities.Complaint;
import com.example.demo.entities.Type;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepo extends JpaRepository<Complaint,Integer> {
    void deleteComplaintById(int id);
    Optional<Complaint> findComplaintById(int id);
    List<Complaint> findComplaintByType(Type type);
    


}
