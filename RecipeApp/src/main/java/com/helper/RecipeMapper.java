package com.helper;

public class RecipeMapper {
    private final Ingredient ingredient;
    private final int amount;

    public RecipeMapper(Ingredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getAmount() {
        return amount;
    }
}
