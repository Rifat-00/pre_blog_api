package com.spring_project.pre_blog_api.Services;

import com.spring_project.pre_blog_api.Entities.User;
import com.spring_project.pre_blog_api.Payloads.UserDTO;

import java.util.List;


public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, Integer id);
    UserDTO getUserById(Integer id);
    void deleteUser(Integer id);
}
