package com.nd.amrhal.bakingapp.Recipes;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesInterface {

    @GET("baking.json")
    Call<List<RecipeModel>> getRecipes();
}
