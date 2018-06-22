package com.nd.amrhal.bakingapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nd.amrhal.bakingapp.Recipes.RecipeModel;

public class DetailActivity extends AppCompatActivity {
    boolean mTwopane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        RecipeModel recipeModel = getIntent().getParcelableExtra(RecipesActivity.RECIPE_PARC_KEY);
        if (findViewById(R.id.linearLayoutforStepfragment) != null) {
            mTwopane = true;
            setupRecipeDetailsFragment();
            setupRecipeStepDetailsfragment();
            Toast.makeText(this, "tablet", Toast.LENGTH_SHORT).show();
        }else{

            setupRecipeDetailsFragment();
            Toast.makeText(this, "phone", Toast.LENGTH_SHORT).show();

        }
        Toast.makeText(this, "name is " + recipeModel.getName(), Toast.LENGTH_SHORT).show();
    }

    private void setupRecipeDetailsFragment() {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.FragmentDetail, recipeDetailFragment)
                .commit();
    }

    private void setupRecipeStepDetailsfragment() {
        RecipeStepDetailFragment recipeDetailStepFragment = new RecipeStepDetailFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.FragmentStepDetail, recipeDetailStepFragment)
                .commit();
    }

}
