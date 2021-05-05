package com.helper;

import java.util.Set;

public class DishPreference {
    private final Set<Ingredient> preferredIngredients;
    private final Set<Ingredient> notPreferredIngredients;

    public DishPreference(Set<Ingredient> preferredIngredients, Set<Ingredient> notPreferredIngredients) {
        this.preferredIngredients = preferredIngredients;

        this.notPreferredIngredients = notPreferredIngredients;
    }

/*
    "dishPreference" : {
        "preferredIngredients" : ["POTATO","ONION"]
        }
 */

    public Set<Ingredient> getPreferredIngredients() {
        return preferredIngredients;
    }

    public Set<Ingredient> getNotPreferredIngredients() {
        return notPreferredIngredients;
    }
}
