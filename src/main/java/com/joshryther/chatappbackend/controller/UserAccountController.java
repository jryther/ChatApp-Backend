package com.joshryther.chatappbackend.controller;


import com.joshryther.chatappbackend.config.JwtUtil;
import com.joshryther.chatappbackend.model.DBUser;
import com.joshryther.chatappbackend.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserAccountController {

    JwtUtil jwtUtil;
    MongoService mongoService;

    @Autowired
    UserAccountController (JwtUtil jwtUtil, MongoService mongoService) {
        this.jwtUtil = jwtUtil;
        this.mongoService = mongoService;
    }

    @GetMapping (value="/userdetails")
    public ResponseEntity<?> getUserName(@RequestHeader Map<String, Object> payLoad) {
        String token = (String) payLoad.get("authorization");
        String userName = jwtUtil
                .getUsernameFromToken(token.substring(7));

        DBUser dbUser = mongoService.findUserByEmail(userName);

        Map<String, String> map = new HashMap<String, String>();
        map.put("firstname", dbUser.getFirstName());
        map.put("lastname", dbUser.getLastName());
        map.put("email", dbUser.getEmail());
        map.put("uid", dbUser.getUid().toString());
        return ResponseEntity.ok(map);
    }

    @PostMapping (value="/authenticate/createuser")
    public ResponseEntity<?> createUser(@RequestBody Map<String, Object> payLoad) {
        String email = (String) payLoad.get("email");

        if(mongoService.findUserByEmail(email) == null) {
            String firstName = (String) payLoad.get("firstName");
            String lastName = (String) payLoad.get("lastName");
            String password = (String) payLoad.get("password");

            DBUser dbUser = new DBUser(mongoService.findNextUid(), firstName, lastName, email, password);
            mongoService.addUser(dbUser);
            return ResponseEntity.ok("success");
        }
        else {
            System.out.println(mongoService.findUserByEmail(email).getEmail());
            return ResponseEntity.ok("user already created");
        }
    }

    @PostMapping(value="/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, Object> payload){
        String uid = (String) payload.get("uid");
        String firstName = (String) payload.get("firstName");
        String lastName = (String) payload.get("lastName");
        String email = (String) payload.get("email");
        String password = (String) payload.get("password");

        DBUser dbUserOld = mongoService.findUserByID(Integer.parseInt(uid));

        if (password == null) {
            password = dbUserOld.getPassword();;
        }

        if (dbUserOld == null) {
            throw new IllegalStateException("no user found");
        }

        DBUser dbUserNew = new DBUser(Integer.parseInt(uid), firstName, lastName, email, password);
        mongoService.updateUser(dbUserOld, dbUserNew);
        return ResponseEntity.ok("success");
    }
}
