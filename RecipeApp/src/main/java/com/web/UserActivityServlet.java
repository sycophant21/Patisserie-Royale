package com.web;

import com.domain.classes.User;
import com.helper.LoginStatus;
import com.helper.Serializer;
import com.helper.Status;
import com.manager.ActivityManager;
import com.manager.UserManager;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

public class UserActivityServlet extends HttpServlet {
    private final UserManager userManager;
    private final Serializer serializer;
    private final ActivityManager activityManager;

    public UserActivityServlet(UserManager userManager, Serializer serializer, ActivityManager activityManager) {
        this.userManager = userManager;
        this.serializer = serializer;
        this.activityManager = activityManager;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String details = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
        User userReceived = (User) serializer.deserialize(details, User.class);
        User userRetrieved = userManager.getUserFromUserID(userReceived.getUserID());
        String statusToReturn;
        if (userRetrieved != null) {
            statusToReturn = "User already exists";
        }
        else {
            if (userManager.createUser(userReceived)) {
                statusToReturn = "User successfully created";
            }
            else {
                statusToReturn = "User could not be created, try again later";
            }
        }
        resp.getWriter().println(serializer.serialize(new Status(statusToReturn)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String details = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
        User userReceived = (User) serializer.deserialize(details, User.class);
        User userRetrieved = userManager.getUserFromUserID(userReceived.getUserID());
        String statusToReturn;
        if (userRetrieved != null) {
            if (userReceived.getPassword().equals(userRetrieved.getPassword())) {
                UUID uuid = UUID.randomUUID();
                if (activityManager.addNewActiveUser(uuid.toString(), userReceived.getUserID())) {
                    statusToReturn = "User authenticated";
                    resp.getWriter().println(serializer.serialize(new LoginStatus(new Status(statusToReturn), uuid)));
                }
                else {
                    statusToReturn = "Already logged in";
                    resp.getWriter().println(serializer.serialize(new Status(statusToReturn)));
                }
            } else {
                statusToReturn = "Password Entered is incorrect";
                resp.getWriter().println(serializer.serialize(new Status(statusToReturn)));
            }
        }
        else {
            statusToReturn = "User doesn't exist";
            resp.getWriter().println(serializer.serialize(new Status(statusToReturn)));
        }
    }
}
