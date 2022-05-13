package com.joshryther.chatappbackend.config;

//TODO: Move this to application.properties
public class AgoraRepo {
    public static String appId = "${agora.appID}";
    public static String appCertificate ="${agora.appCertificate}";
    public static String channelName = "Test";
    public static int uid = 1; // By default 1
    public static int expirationTimeInSeconds = 3600; // By default 3600
    public static int role = 1; // By default Publisher
}
