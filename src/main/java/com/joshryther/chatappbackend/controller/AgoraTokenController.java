package com.joshryther.chatappbackend.controller;

import com.joshryther.chatappbackend.service.AgoraTokenBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AgoraTokenController {

    @Autowired
    AgoraTokenBuilderService agoraTokenBuilderService;

    @PostMapping(value = "/fetch_rtc_token")
    public String AgoraToken (@RequestBody Map<String, Object> payLoad){
        String channelName = (String) payLoad.get("channelName");
        int uid = (int) payLoad.get("uid");
        int role = (int) payLoad.get("role");

        return agoraTokenBuilderService
                .buildToken(channelName,
                        uid,
                        role);
    }


}
