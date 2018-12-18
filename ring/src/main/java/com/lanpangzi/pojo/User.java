package com.lanpangzi.pojo;

import java.io.Serializable;

public class User implements Serializable {
    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
