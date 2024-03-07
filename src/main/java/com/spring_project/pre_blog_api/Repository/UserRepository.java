package com.spring_project.pre_blog_api.Repository;

import com.spring_project.pre_blog_api.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
