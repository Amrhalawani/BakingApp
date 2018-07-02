package com.nd.amrhal.bakingapp;


import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.widget.Toast;

import com.nd.amrhal.bakingapp.Ingredient.IngredientModel;
import com.nd.amrhal.bakingapp.Recipes.RecipeModel;


import java.util.List;

import com.nd.amrhal.bakingapp.Keys;

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
        getSharedPreferences(Keys.TWO_PANE_SHAREDP, MODE_PRIVATE).edit().putInt(Keys.TWO_PANE_PHONE_OR_TABLET, Keys.PHONE).apply();
        recipeModel = getIntent().getParcelableExtra(RecipesActivity.RECIPE_PARC_KEY);

        if (findViewById(R.id.linearLayoutforStepfragment) != null) {
            mTwopane = true;
            getSharedPreferences(Keys.TWO_PANE_SHAREDP, MODE_PRIVATE).edit().putInt(Keys.TWO_PANE_PHONE_OR_TABLET, Keys.TABLET).apply();
            if (savedInstanceState == null) {
                setupRecipeDetailsFragment();
                setupRecipeStepDetailsfragment();

            }
        } else {
            mTwopane = false;
            getSharedPreferences(Keys.TWO_PANE_SHAREDP, MODE_PRIVATE).edit().putInt(Keys.TWO_PANE_PHONE_OR_TABLET, Keys.PHONE).apply();

            if (savedInstanceState == null) {
                setupRecipeDetailsFragment();
            }

        }

        int check_phone_or_tablet = getSharedPreferences(Keys.TWO_PANE_SHAREDP, MODE_PRIVATE).getInt(Keys.TWO_PANE_PHONE_OR_TABLET, -1);

        Toast.makeText(this,  checkPhoneOrTablet(check_phone_or_tablet) , Toast.LENGTH_SHORT).show();

    }

    private String checkPhoneOrTablet(int i) {
        String result = "not specifec Phone or Tablet";
        if (i == Keys.PHONE) {
            result = "it's a Phone";
        } else if (i == Keys.TABLET) {
            result = "it's a Tablet";
        }
        return result;
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
