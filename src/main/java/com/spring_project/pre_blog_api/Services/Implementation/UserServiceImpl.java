package com.spring_project.pre_blog_api.Services.Implementation;


import com.spring_project.pre_blog_api.Entities.User;
import com.spring_project.pre_blog_api.Exception.UserNotFoundException;
import com.spring_project.pre_blog_api.Payloads.UserDTO;
import com.spring_project.pre_blog_api.Repository.UserRepository;
import com.spring_project.pre_blog_api.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = repository.findAll();
        return users.stream().map(this::entityDtoConverter).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoEntityConverter(userDTO);
        User savedUser = repository.save(user);
        return entityDtoConverter(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User user = repository.findById(userId)
                        .orElseThrow(()->  new UserNotFoundException("user not found"));
        User savedUser = dtoEntityConverter(userDTO);
        repository.save(savedUser);
        return entityDtoConverter(savedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = repository.findById(userId)
                    .orElseThrow(()-> new UserNotFoundException("user not found"));
        return entityDtoConverter(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = repository.findById(userId)
                    .orElseThrow(()-> new UserNotFoundException("user not found"));
        repository.delete(user);
    }

    public User dtoEntityConverter(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }

    public UserDTO entityDtoConverter(User user){
        return modelMapper.map(user,UserDTO.class);
    }
}
