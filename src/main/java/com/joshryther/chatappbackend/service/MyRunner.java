package com.joshryther.chatappbackend.service;

import com.joshryther.chatappbackend.model.DBUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);


    @Autowired
    private MongoService mongoService;
    @Autowired
    private PresignedS3URLService presignedS3URLService;

    @Override
    public void run(String... args) throws Exception {
    }
}