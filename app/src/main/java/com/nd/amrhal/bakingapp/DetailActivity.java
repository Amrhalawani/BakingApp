package com.nd.amrhal.bakingapp;


import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.MenuItem;
import android.widget.Toast;

import com.nd.amrhal.bakingapp.Models.IngredientModel;
import com.nd.amrhal.bakingapp.Models.RecipeModel;

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(recipeModel.getName());
            getSupportActionBar().setElevation(20);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.cupcake40);

        }

        if (findViewById(R.id.linearLayoutforStepfragment) != null) {
            mTwopane = true;


            Util.setPhoneOrTablet(DetailActivity.this, Util.TABLET);
            if (savedInstanceState == null) {
                setupRecipeDetailsFragment();
                setupRecipeStepDetailsfragment();

            }
        } else {
            mTwopane = false;
            Util.setPhoneOrTablet(DetailActivity.this, Util.PHONE);

            if (savedInstanceState == null) {
                setupRecipeDetailsFragment();
            }

        }

        Toast.makeText(this, "phone or tablet" + Util.getPhoneOrTablet(DetailActivity.this), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Util.setPositionfortabletonly(this, 0);
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
    public void sendData(int message) {

        RecipeStepDetailFragment f = (RecipeStepDetailFragment) getSupportFragmentManager().findFragmentByTag("fragment_tag_fragmentStepDetail");
        f.displayReceivedData(message);


    }
}
