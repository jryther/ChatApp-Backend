package com.joshryther.chatappbackend.service;

import com.joshryther.chatappbackend.model.DBUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private MongoService mongoService;

    @Autowired
    public MyUserDetailsService(MongoService mongoService) {
    this.mongoService = mongoService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        DBUser dbUser = mongoService.findUserByEmail(s);
        String email = dbUser.getEmail();
        String password = dbUser.getPassword();

        return new User(email, password,
                new ArrayList<>());
    }
}