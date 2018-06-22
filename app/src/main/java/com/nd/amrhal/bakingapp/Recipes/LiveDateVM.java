package com.nd.amrhal.bakingapp.Recipes;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveDateVM extends ViewModel {

    final String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    // Create a LiveData with a String
    private MutableLiveData<List<RecipeModel>> mCurrentName;


    public MutableLiveData<List<RecipeModel>> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<>();
            getRecipes();
        }
        //mCurrentName.postValue(respons[0]);
        return mCurrentName;
    }


    public void getRecipes() {
        final String[] respons = {"000"};
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipesInterface recipesInterface = retrofit.create(RecipesInterface.class);

        recipesInterface.getRecipes().enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                List<RecipeModel> recipeModels = response.body();
                if (recipeModels != null) {
                    mCurrentName.postValue(recipeModels);
                    Log.e("tag", "onResponse: recipeModels.size() = " + recipeModels.size());
                }

            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                t.getLocalizedMessage();
           Log.e("tag", "failure to retrive data");
            }
        });
    }

// Rest of the ViewModel...
}