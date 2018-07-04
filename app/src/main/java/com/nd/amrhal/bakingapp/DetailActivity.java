package com.nd.amrhal.bakingapp;


import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.widget.Toast;

import com.nd.amrhal.bakingapp.Ingredient.IngredientModel;
import com.nd.amrhal.bakingapp.Recipes.RecipeModel;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements RecipeDetailFragment.SendMessage {

    final static String INGREDIANT_LIST_KEY = "ingrediantlist";

    RecipeModel recipeModel;

    List<IngredientModel> arrayList;

    boolean mTwopane = false;

    FragmentManager fragmentManager;
    RecipeStepDetailFragment recipeDetailStepFragment;


    SharedPreferences shPref;
    SharedPreferences.Editor shEditor;
    //***//tosave
    //getSharedPreferences(SHAREDP_KEY, MODE_PRIVATE).edit().putInt(SHAREDP_INT_KEY, POPselected).apply();
    //***//toget
    //getSharedPreferences(SHAREDP_KEY, MODE_PRIVATE).getInt("name", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recipeModel = getIntent().getParcelableExtra(RecipesActivity.RECIPE_PARC_KEY);

        if (findViewById(R.id.linearLayoutforStepfragment) != null) {
            mTwopane = true;


            Util.setPhoneOrTablet(DetailActivity.this,Util.TABLET);
            if (savedInstanceState == null) {
                setupRecipeDetailsFragment();
                setupRecipeStepDetailsfragment();

            }
        } else {
            mTwopane = false;
            Util.setPhoneOrTablet(DetailActivity.this,Util.PHONE);

            if (savedInstanceState == null) {
                setupRecipeDetailsFragment();
            }

        }

        Toast.makeText(this, "phone or tablet"+Util.getPhoneOrTablet(DetailActivity.this), Toast.LENGTH_SHORT).show();

    }


    private void setupRecipeDetailsFragment() {

//       RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
//        Bundle bundle = new Bundle();
//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//               .add(R.id.FragmentDetail,recipeDetailFragment).commit();
    }

    private void setupRecipeStepDetailsfragment() {
        //recipeDetailStepFragment = new RecipeStepDetailFragment();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.FragmentStepDetail, recipeDetailStepFragment)
//                .commit()


    }

    @Override
    public void sendData(String message) {

        RecipeStepDetailFragment f = (RecipeStepDetailFragment) getSupportFragmentManager().findFragmentByTag("fragment_tag_fragmentStepDetail");
        f.displayReceivedData(message);


    }
}
