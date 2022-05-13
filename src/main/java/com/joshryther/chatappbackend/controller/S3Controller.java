package com.joshryther.chatappbackend.controller;

import com.joshryther.chatappbackend.model.DBUser;
import com.joshryther.chatappbackend.service.MongoService;
import com.joshryther.chatappbackend.service.PresignedS3URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class S3Controller {

    PresignedS3URLService presignedS3URLService;
    MongoService mongoService;

    @Autowired
    S3Controller(PresignedS3URLService presignedS3URLService, MongoService mongoService){
        this.presignedS3URLService = presignedS3URLService;
        this.mongoService = mongoService;
    }

    @GetMapping("/GetPresignedURL/{email}")
    public String getPresignedUrl(@PathVariable("email") String email) {
        DBUser dbUser = mongoService.findUserByEmail(email);
        String url = presignedS3URLService.getBucketURL(Integer.toString(dbUser.getUid()) + ".JPG");
        return url;
    }

    @GetMapping("/PostPresignedURL/{email}")
    public String postPresignedUrl(@PathVariable("email") String email) {
        DBUser dbUser = mongoService.findUserByEmail(email);
        String url = presignedS3URLService.putBucketURL(Integer.toString(dbUser.getUid()) + ".JPG");
        return url;
    }
}
