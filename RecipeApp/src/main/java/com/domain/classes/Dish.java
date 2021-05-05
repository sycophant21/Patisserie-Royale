package com.domain.classes;

import com.domain.id.DishID;
import com.domain.id.UserID;

public class Dish {
    private final String dishName;
    private final DishID dishID;
    private final UserID uploadedBy;
    private final Recipe dishRecipe;

    public Dish(String dishName, DishID dishID, UserID uploadedBy, Recipe dishRecipe) {
        this.dishName = dishName;
        this.dishID = dishID;
        this.uploadedBy = uploadedBy;
        this.dishRecipe = dishRecipe;
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
        "recipeID" : {
            "recipeId" : "abc"
            },
            "ingredients" : [
            {
            "ingredient" : "Onion", "amount" : 15
            },
            {
            "ingredient" : "Potato", "amount" : 10
            }
            ]
        }
}
 */

    public UserID getUploadedBy() {
        return uploadedBy;
    }

    public Recipe getDishRecipe() {
        return dishRecipe;
    }

    public String getDishName() {
        return dishName;
    }

    public DishID getDishID() {
        return dishID;
    }
}

