package com.spnsolo;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ServletReport {
    private Map<String,String> userInfo;

    public ServletReport(){
        userInfo = new ConcurrentHashMap<>();
    }
    public void addUserInfo(String ip,String userAgent){
        userInfo.put(ip,userAgent);
    }
    public Map<String,String> getUserInfo(){return userInfo;}
}
