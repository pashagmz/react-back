package com.pashagmz.react.service.impl;

import com.pashagmz.react.model.User;
import com.pashagmz.react.repository.UserRepository;
import com.pashagmz.react.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository repository;


    @Autowired
    public DefaultUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) {
        User user = repository.findByEmail(email);

        if (null == user) {
            throw new UsernameNotFoundException(String.format("User with email %s not found", email));
        }
        return user;
    }

}
