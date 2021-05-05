package com.manager;

import com.dao.UserDAO;
import com.domain.classes.User;
import com.domain.id.UserID;

public class UserManager {
    private final UserDAO userDAO;

    public UserManager(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserFromUserID(UserID userID) {
        return userDAO.getUserFromUserID(userID);
    }


    public boolean createUser(User user) {
       return userDAO.createUser(user);
    }
}
