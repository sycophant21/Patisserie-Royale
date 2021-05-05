package com.web;

import com.domain.classes.Dish;
import com.domain.classes.Recipe;
import com.helper.DishFinder;
import com.helper.Serializer;
import com.helper.Status;
import com.manager.ActivityManager;
import com.manager.DishManager;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class DishHandlerServlet extends HttpServlet {
    private final DishManager dishManager;
    private final Serializer serializer;
    private final ActivityManager activityManager;

    public DishHandlerServlet(DishManager dishManager, Serializer serializer, ActivityManager activityManager) {
        this.dishManager = dishManager;
        this.serializer = serializer;
        this.activityManager = activityManager;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (activityManager.isUserAuthenticated(req.getHeader("UUID"))) {
            String details = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
            resp.getWriter().println(serializer.serialize(dishManager.findDishes((DishFinder) serializer.deserialize(details, DishFinder.class))));
        } else {
            resp.getWriter().println("Login first");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (activityManager.isUserAuthenticated(req.getHeader("UUID"))) {
            String details = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
            Dish tempDish = (Dish) serializer.deserialize(details, Dish.class);
            Recipe recipe = new Recipe(tempDish.getDishRecipe().getRecipeID(), tempDish.getDishRecipe().getIngredients());
            Dish dishToBeCreated = new Dish(tempDish.getDishName(), tempDish.getDishID(), tempDish.getUploadedBy(), recipe);
            boolean isCreated = dishManager.createDish(dishToBeCreated);
            String statusToReturn;
            if (isCreated) {
                statusToReturn = "Recipe Successfully created";
            } else {
                statusToReturn = "Recipe could not be created";
            }
            resp.getWriter().println(serializer.serialize(new Status(statusToReturn)));
        } else {
            resp.getWriter().println("Login first");
        }
    }
}
/*
{
    "dishName" : "Aaloo_Pyaaz",
    "dishID" : {
        "dishId" : "dish_01"
        },
    "uploadedBy" : {
        "userId" : "12345"
        },
    "dishRecipe" : {
        "recipeID": {
            "recipeId":"abc"
            },
            "ingredients" :
            [   {
                "ingredient":"Onion", "amount":15
                },
                {
                "ingredient":"Potato","amount":10
                }
            ]
        }
}
 */