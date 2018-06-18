package com.nd.amrhal.bakingapp.Recipes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nd.amrhal.bakingapp.RecipesActivity;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecipesCallRetrofit {

    final String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    public String getRecipes() {
        final String[] respons = {"000"};
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();

        RecipesInterface recipesInterface = retrofit.create(RecipesInterface.class);

        recipesInterface.getRecipes().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    Log.e("tag", "Retrofit onResponse: get response");
                    try {
                        respons[0] = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("tag", "Retrofit onResponse: NOoooooooo response");
            }
        });

        return respons[0];
    }


}
