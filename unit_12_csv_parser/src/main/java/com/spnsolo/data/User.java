package com.spnsolo.data;

import com.spnsolo.annotation.DeserializeField;
import com.spnsolo.en.Role;

public class User {
    @DeserializeField("id")
    private Integer id;
    @DeserializeField("login")
    private String login;
    @DeserializeField("password")
    private String password;
    @DeserializeField("available")
    private Boolean available;
    @DeserializeField("role")
    private Role role;

    @Override
    public String toString() {
        return "User { " +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", available=" + available +
                ", role=" + role +
                '}';
    }
}
