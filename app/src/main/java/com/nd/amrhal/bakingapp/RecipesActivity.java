package com.nd.amrhal.bakingapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.nd.amrhal.bakingapp.Recipes.LiveDateVM;


public class RecipesActivity extends AppCompatActivity {

    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
TextView textView;
//    Icon made by Freepik from www.flaticon.com
private LiveDateVM mLiveDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        textView =  findViewById(R.id.textview);
        // Get the ViewModel.
        mLiveDate = ViewModelProviders.of(this).get(LiveDateVM.class);


        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                textView.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mLiveDate.getCurrentName().observe(this, nameObserver);
    }
    }

