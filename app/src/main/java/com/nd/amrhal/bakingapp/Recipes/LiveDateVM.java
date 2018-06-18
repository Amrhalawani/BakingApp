package com.nd.amrhal.bakingapp.Recipes;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LiveDateVM extends ViewModel {
    final String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    // Create a LiveData with a String
    private MutableLiveData<String> mCurrentName;


    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
           getRecipes();
        }
        return mCurrentName;
    }


    public void getRecipes() {
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
                        mCurrentName.postValue(respons[0]);
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
    }

// Rest of the ViewModel...
}