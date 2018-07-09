package com.nd.amrhal.bakingapp.Recipes;

import com.nd.amrhal.bakingapp.Models.RecipeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesInterface {

    @GET("baking.json")
    Call<List<RecipeModel>> getRecipes();
}
