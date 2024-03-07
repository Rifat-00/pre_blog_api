package com.spring_project.pre_blog_api.Controllers;


import com.spring_project.pre_blog_api.Payloads.UserDTO;
import com.spring_project.pre_blog_api.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserService service;


    public UserResource(UserService service) {
        this.service = service;
    }

    //create new User
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createUserDTO = service.createUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }

    //update a user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId){
        UserDTO updatedUser = service.updateUser(userDTO,userId);
        return ResponseEntity.ok(updatedUser);
    }

    //delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        service.deleteUser(userId);
        return new ResponseEntity<>(Map.of("message","user deleted"), HttpStatus.OK);
    }

    //get all user
    @GetMapping("/")
    public List<UserDTO> fetchAllUser(){
        return service.getAllUsers();
    }

    //fetch all user
    @GetMapping("/{userId}")
    public UserDTO fetchUserById(@PathVariable Integer userId){
        return service.getUserById(userId);
    }

}
