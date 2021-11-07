package com.example.demo.repo;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {


    @Modifying
    @Query("delete from User where email=?1")
    void deleteUserByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    Optional<User> findUserByEmail(String email);}
