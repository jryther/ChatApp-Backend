package com.joshryther.chatappbackend.service;

import agora.media.RtcTokenBuilder;
import com.joshryther.chatappbackend.model.AgoraToken;
import org.springframework.stereotype.Service;

@Service
public class AgoraTokenBuilderService {

    public String buildToken(String channelName, int uid, int role) {
        RtcTokenBuilder.Role tokenRole;

        RtcTokenBuilder rtcTokenBuilder = new RtcTokenBuilder();
        AgoraToken agoraToken = new AgoraToken(channelName, uid, role);

        if (agoraToken.getRole() == 1) {
            tokenRole = RtcTokenBuilder.Role.Role_Publisher;
        }
        else {
            tokenRole = RtcTokenBuilder.Role.Role_Subscriber;
        }

        int timeStamp = (int)(System.currentTimeMillis() / 1000 + agoraToken.getExpirationTimeInSeconds());

        String token = rtcTokenBuilder.buildTokenWithUid(
                agoraToken.getAppId(),
                agoraToken.getAppCertificate(),
                agoraToken.getChannelName(),
                agoraToken.getUid(),
                tokenRole,
                timeStamp);

        return token;
    }
}