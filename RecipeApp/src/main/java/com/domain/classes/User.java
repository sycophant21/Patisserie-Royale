package com.domain.classes;

import com.domain.id.UserID;

public class User {
    private final String userName;
    private final UserID userID;
    private final String password;

    public User(String userName, UserID userID, String password) {
        this.userName = userName;
        this.userID = userID;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public UserID getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }
}
/*
    "userName" : "Admin",
    "userID" : {
        "userId" : "12345"
        },
    "password" : "abcd"
 */
