package com.demo.test.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/27.
 */

public class TicketAuth implements Serializable
{

    public String userId ;

    private String username;

    private String password;

    public TicketAuth() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
