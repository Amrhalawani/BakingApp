package com.nd.amrhal.bakingapp.Recipes;


import com.google.gson.annotations.SerializedName;

public class RecipeModel {
    @SerializedName("id")
    String mId;
    @SerializedName("name")
    String mName;

    String servings;

    public RecipeModel(String id, String name, String servings) {
        this.mId = id;
        this.mName = name;
        this.servings = servings;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getServings() {
        return servings;
    }
}
