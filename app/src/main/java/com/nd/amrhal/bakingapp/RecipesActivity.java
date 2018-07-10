package com.nd.amrhal.bakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.nd.amrhal.bakingapp.IdlingResource.SimpleIdlingResource;
import com.nd.amrhal.bakingapp.Recipes.LiveDateVM;
import com.nd.amrhal.bakingapp.Models.RecipeModel;
import com.nd.amrhal.bakingapp.Recipes.RecipesRecyclerAdaptor;


import java.util.List;


public class RecipesActivity extends AppCompatActivity {

    //widget doeasn't work with constratnlayout
    //http://www.jsonschema2pojo.org/

    final static String RECIPE_PARC_KEY = "recipemodel";
// SwipeRefreshLayout mPullToRefresh = findViewById(R.id.swiperefresh_recipesactivity);

    Context context;

    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    //    Icon made by Freepik from www.flaticon.com
    private LiveDateVM mLiveDate;
    @Nullable
    SimpleIdlingResource mSimpleIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setSaveEnabled(true);
        final RecipesRecyclerAdaptor recyclerAdaptor = new RecipesRecyclerAdaptor(RecipesActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(RecipesActivity.this, numberOfColumns());
        recyclerView.setLayoutManager(gridLayoutManager);

        // Get the ViewModel.
        mLiveDate = ViewModelProviders.of(this).get(LiveDateVM.class);
        // Create the observer which updates the UI.
        Observer<List<RecipeModel>> nameObserver = new Observer<List<RecipeModel>>() {
            @Override
            public void onChanged(@Nullable final List<RecipeModel> recipeModels) {
                recyclerAdaptor.updateData(recipeModels);
                recyclerView.setAdapter(recyclerAdaptor);

                mSimpleIdlingResource.setIdleState(true);

                recyclerAdaptor.setOnItemClickListener(new RecipesRecyclerAdaptor.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.e("TAG", "onItemClick: pos" + position);

                        RecipeModel recipeModel = recipeModels.get(position);

                        Intent intent = new Intent(RecipesActivity.this, DetailActivity.class);

                        intent.putExtra(RECIPE_PARC_KEY, recipeModel); //Parcelable

                        startActivity(intent);

                    }
                });

            }
        };
        //for esspresso Test
        getIdlingResource();

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mLiveDate.getCurrentName().observe(this, nameObserver);

//        mPullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(RecipesActivity.this, "on refresh", Toast.LENGTH_SHORT).show();
//            }
//        });

    }


    public IdlingResource getIdlingResource() {
        if (mSimpleIdlingResource == null) {
            mSimpleIdlingResource = new SimpleIdlingResource();
        }
        return mSimpleIdlingResource;
    }

    public int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        // You can change this divider to adjust the size of the poster
        int widthDivider = 550;
        if (width < 1079 && width > 1000) {
            widthDivider= 500;
        }
        Toast.makeText(this, "width of this mobile is " + width, Toast.LENGTH_LONG).show();
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 1; //to keep the grid aspect
        return nColumns;
    }


}

