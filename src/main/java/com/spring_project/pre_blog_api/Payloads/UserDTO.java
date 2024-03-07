package com.spring_project.pre_blog_api.Payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Integer id;
    @NotNull
    @Size(min = 3,max = 16)
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 20,max = 1000)
    private String bio;
    @NotNull
    @Size(min = 6,max = 32)
    private String password;
}
