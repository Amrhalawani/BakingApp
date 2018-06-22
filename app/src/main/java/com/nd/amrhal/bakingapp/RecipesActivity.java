package com.nd.amrhal.bakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.nd.amrhal.bakingapp.Recipes.LiveDateVM;
import com.nd.amrhal.bakingapp.Recipes.RecipeModel;
import com.nd.amrhal.bakingapp.Recipes.RecipesRecyclerAdaptor;


import java.util.List;


public class RecipesActivity extends AppCompatActivity {

    //widget doeasn't work with constratnlayout
    //http://www.jsonschema2pojo.org/

    Context context;

    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    TextView textView;
    //    Icon made by Freepik from www.flaticon.com
    private LiveDateVM mLiveDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        textView = findViewById(R.id.textview);
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
                textView.setText("List size: " + recipeModels.size());
                recyclerAdaptor.updateData(recipeModels);
                recyclerView.setAdapter(recyclerAdaptor);
                recyclerAdaptor.setOnItemClickListener(new RecipesRecyclerAdaptor.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.e("TAG", "onItemClick: pos" + position);
                        RecipeModel recipeModel = recipeModels.get(position);

                        Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                        intent.putExtra("id",recipeModel.getId());
                        startActivity(intent);
                        Toast.makeText(RecipesActivity.this, "pos " + position+" And id ="+recipeModel.getId(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mLiveDate.getCurrentName().observe(this, nameObserver);



    }

    public int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 600;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 1; //to keep the grid aspect
        return nColumns;
    }

}

