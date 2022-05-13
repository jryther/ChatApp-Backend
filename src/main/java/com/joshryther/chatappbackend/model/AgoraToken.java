package com.joshryther.chatappbackend.model;

import com.joshryther.chatappbackend.config.AgoraRepo;

public class AgoraToken {

    private String appId;
    private String appCertificate;
    private String channelName;
    private int uid;
    private int expirationTimeInSeconds;
    private int role;

    public AgoraToken() {
        this.appId = AgoraRepo.appId;
        this.appCertificate = AgoraRepo.appCertificate;
        this.channelName = AgoraRepo.channelName;
        this.uid = AgoraRepo.uid;
        this.expirationTimeInSeconds = AgoraRepo.expirationTimeInSeconds;
        this.role = AgoraRepo.role;
    }

    public AgoraToken(String channelName, int uid, int role) {
        this.appId = AgoraRepo.appId;
        this.appCertificate = AgoraRepo.appCertificate;
        this.channelName = channelName;
        this.uid = uid;
        this.expirationTimeInSeconds = AgoraRepo.expirationTimeInSeconds;
        this.role = role;
    }

    public String getAppId() {
        return appId;
    }


    public String getAppCertificate() {
        return appCertificate;
    }


    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getExpirationTimeInSeconds() {
        return expirationTimeInSeconds;
    }

    public void setExpirationTimeInSeconds(int expirationTimeInSeconds) {
        this.expirationTimeInSeconds = expirationTimeInSeconds;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
