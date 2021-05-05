package com.dao;

import com.google.gson.Gson;
import com.helper.Serializer;
import com.manager.ActivityManager;
import com.manager.DishFilterManager;
import com.manager.DishManager;
import com.manager.UserManager;
import com.mongo.FilterToBSONConverter;
import com.mongo.MongoOperations;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.web.DishHandlerServlet;
import com.web.UserActivityServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.setConnectors(new Connector[]{connector});
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
        MongoClient mongoClient = MongoClients.create(
                args[0]);
        MongoDatabase database = mongoClient.getDatabase(args[1]);
        MongoOperations mongoOperations = new MongoOperations(database, new FilterToBSONConverter());
        UserDAO userDAO = new UserDAO(mongoOperations, new Serializer(new Gson()));
        DishDAO dishDAO = new DishDAO(mongoOperations, new Serializer(new Gson()), new DishFilterManager());
        UserManager userManager = new UserManager(userDAO);
        DishManager dishManager = new DishManager(dishDAO);
        ActivityManager activityManager = new ActivityManager(new HashMap<>());
        servletHandler.addServletWithMapping(new ServletHolder(new UserActivityServlet(userManager, new Serializer(new Gson()), activityManager)), "/manageUserActivity");
        servletHandler.addServletWithMapping(new ServletHolder(new DishHandlerServlet(dishManager, new Serializer(new Gson()), activityManager)), "/manageDishActivity");

        server.start();
    }
}