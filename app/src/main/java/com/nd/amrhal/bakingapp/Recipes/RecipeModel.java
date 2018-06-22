package com.nd.amrhal.bakingapp.Recipes;

import com.google.gson.annotations.SerializedName;
import com.nd.amrhal.bakingapp.Ingredient.IngredientModel;
import com.nd.amrhal.bakingapp.Step.StepModel;

import java.util.List;

public class RecipeModel {

    public RecipeModel(int id, String name, List<IngredientModel> ingredients, List<StepModel> steps, int servings) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }

    @SerializedName("id")
    private int id;

    @SerializedName("name")

    private String name;

    @SerializedName("ingredients")
    private List<IngredientModel> ingredients = null;
    @SerializedName("steps")

    private List<StepModel> steps = null;

    @SerializedName("servings")
    private int servings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(List<StepModel> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }



//    @SerializedName("id")
//    String mId;
//    @SerializedName("name")
//    String mName;
//
//    String servings;
//
//    public RecipeModel(String id, String name, String servings) {
//        this.mId = id;
//        this.mName = name;
//        this.servings = servings;
//    }
//
//    public String getId() {
//        return mId;
//    }
//
//    public String getName() {
//        return mName;
//    }
//
//    public String getServings() {
//        return servings;
//    }
}
