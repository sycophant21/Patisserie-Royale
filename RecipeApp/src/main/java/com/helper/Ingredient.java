package com.helper;

import com.google.gson.annotations.SerializedName;


public enum Ingredient {
    @SerializedName("Potato")
    POTATO("Potato"),
    @SerializedName("Onion")
    ONION("Onion"),
    @SerializedName("Garlic")
    GARLIC("Garlic"),
    @SerializedName("Apple")
    APPLE("Apple"),
    @SerializedName("Mango")
    MANGO("Mango"),
    @SerializedName("RawMango")
    RAW_MANGO("RawMango");

    private final String string;

    Ingredient(String string) {

        this.string = string;
    }

    public String getString() {
        return string;
    }
}
