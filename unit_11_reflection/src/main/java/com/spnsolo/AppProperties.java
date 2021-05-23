package com.spnsolo;

import com.spnsolo.annotation.PropertyKey;

public class AppProperties {
    @PropertyKey("url")
    public String urlDB;

    @PropertyKey("username")
    public String userDB;

    public String passDB;

    @Override
    public String toString() {
        return "AppProperties {" +
                "urlDB='" + urlDB + '\'' +
                ", userDB='" + userDB + '\'' +
                ", passDB='" + passDB + '\'' +
                '}';
    }
}
