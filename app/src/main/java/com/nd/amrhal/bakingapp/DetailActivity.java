package com.nd.amrhal.bakingapp;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;

import com.nd.amrhal.bakingapp.Ingredient.IngredientModel;
import com.nd.amrhal.bakingapp.Recipes.RecipeModel;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements Communication {

    final static String INGREDIANT_LIST_KEY = "ingrediantlist";

    RecipeModel recipeModel;

    List<IngredientModel> arrayList;

    boolean mTwopane = false;

    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recipeModel = getIntent().getParcelableExtra(RecipesActivity.RECIPE_PARC_KEY);

        if (findViewById(R.id.linearLayoutforStepfragment) != null) {
            mTwopane = true;
            if (savedInstanceState == null) {
                setupRecipeDetailsFragment();
                setupRecipeStepDetailsfragment();
                Toast.makeText(this, "tablet", Toast.LENGTH_SHORT).show();
            }
        } else {
            mTwopane = false;
            if (savedInstanceState == null) {
                setupRecipeDetailsFragment();
            }
            Toast.makeText(this, "phone", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "name is " + recipeModel.getName(), Toast.LENGTH_SHORT).show();

    }

    private void setupRecipeDetailsFragment() {

//       RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
//        Bundle bundle = new Bundle();
//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//               .add(R.id.FragmentDetail,recipeDetailFragment).commit();
    }

    private void setupRecipeStepDetailsfragment() {
//        RecipeStepDetailFragment recipeDetailStepFragment = new RecipeStepDetailFragment();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.FragmentStepDetail, recipeDetailStepFragment)
//                .commit();
    }

    //*********************************************************
    @Override
    public void respond(String data) {

        RecipeStepDetailFragment f = (RecipeStepDetailFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentStepDetail);
        f.changeDate(data);

    }
    //*********************************************************
}
