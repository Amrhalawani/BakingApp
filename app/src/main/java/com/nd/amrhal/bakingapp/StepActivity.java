package com.nd.amrhal.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


import com.nd.amrhal.bakingapp.Models.StepModel;

public class StepActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        if (getSupportActionBar() != null) {

            getSupportActionBar().setElevation(20);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            StepModel stepModel = getIntent().getExtras().getParcelable(RecipeDetailFragment.STEP_RECIPE_PARC_KEY);


            if (stepModel != null) {
                String actionbarname = stepModel.getShortDescription();
                getSupportActionBar().setTitle(actionbarname);
            }

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.

                return super.onOptionsItemSelected(item);
        }

    }


}
