package com.springboot.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUserName(String userName);
    
    Optional<User> findByUserNameAndPassword(String userName, String password);
}
