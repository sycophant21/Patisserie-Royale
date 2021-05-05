package com.domain.classes;

import com.domain.id.RecipeID;
import com.helper.Ingredient;
import com.helper.RecipeMapper;

import java.util.HashSet;
import java.util.Set;

public class Recipe {
    private final RecipeID recipeID;
    private final Set<RecipeMapper> ingredients;
    private final Set<Ingredient> ingredientSet;

/*
    "recipeID" : {
        "recipeID" : "Recipe_01"
        },
    "ingredients" : [

        "POTATO" : 25,
        "ONION" : 10
        }

*/

    public Recipe(RecipeID recipeID, Set<RecipeMapper> ingredients) {
        this.recipeID = recipeID;
        this.ingredients = ingredients;
        this.ingredientSet = new HashSet<>();
        ingredients.forEach(k -> ingredientSet.add(k.getIngredient()));

    }

    public Set<Ingredient> getIngredientSet() {
        return ingredientSet;
    }

    public RecipeID getRecipeID() {
        return recipeID;
    }

    public Set<RecipeMapper> getIngredients() {
        return ingredients;
    }
}
