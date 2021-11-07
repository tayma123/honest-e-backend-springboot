package com.example.demo.controlles;

import com.example.demo.entities.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")

public class UserController {
    private  final UserService userService;
    @Autowired
  public UserRepo userRepo;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User Register1 (@RequestBody User user){

        User OldUser = userRepo.findByEmailAndPassword(user.getEmail(),user.getPassword());
        return  OldUser;
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        User user= userService.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser= userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User UpdateUser= userService.updateUser(user);
        return new ResponseEntity<>(UpdateUser, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable("email") String email){
        userService.deleteUser(email);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
