package com.nd.amrhal.bakingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nd.amrhal.bakingapp.Models.IngredientModel;

import com.nd.amrhal.bakingapp.Models.RecipeModel;
import com.nd.amrhal.bakingapp.Models.StepModel;

import java.util.ArrayList;
import java.util.List;


public class RecipeDetailFragment extends Fragment {
    public static final String STEP_RECIPE_PARC_KEY = "stepparclable";
    TextView textView;
    String test = "";
    List<IngredientModel> ingredientModelList = new ArrayList<>();
    List<StepModel> stepModelList = new ArrayList<>();

    RecipeModel recipeModel;

    SendMessage SM;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        textView = view.findViewById(R.id.ingredientTV);

        if (getActivity().getIntent().getExtras() != null) {

            recipeModel = getActivity().getIntent().getExtras().getParcelable(RecipesActivity.RECIPE_PARC_KEY);
            ingredientModelList = recipeModel.getIngredients();
            stepModelList = recipeModel.getSteps();
            setupingredientListtoString(ingredientModelList);
            recyclerview(view, stepModelList);
            Toast.makeText(getActivity(), "get intent", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getActivity(), "we doest get intent", Toast.LENGTH_SHORT).show();
        }

        return view;
    }


    private void recyclerview(View view, final List<StepModel> stepModelList) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_steps);
        StepsRecyclerAdaptor recyclerAdaptor = new StepsRecyclerAdaptor(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerAdaptor.updateData(stepModelList);
        recyclerView.setAdapter(recyclerAdaptor);

        recyclerAdaptor.setOnItemClickListener(new StepsRecyclerAdaptor.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(int position) {
                //tablet
                if (Util.getPhoneOrTablet(getActivity()) == Util.TABLET) {
                    SM.sendData(" from fragment one we send " + position);
                }

                //phone
                else if (Util.getPhoneOrTablet(getActivity()) == Util.PHONE) {

                    Toast.makeText(getActivity(), "it's phone from detail fragment start new step fragment", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), StepActivity.class);
                    StepModel stepModel = stepModelList.get(position);
                    i.putExtra(STEP_RECIPE_PARC_KEY, stepModel); //Parcelable
                    startActivity(i);

//
//                    RecipeStepDetailFragment stepDetailFragment = new RecipeStepDetailFragment();
//                    getActivity().getSupportFragmentManager()
//                            .beginTransaction()
//                            .add(R.id.FragmentDetail, stepDetailFragment)
//                            .commit();
                }
            }
        });
    }

    private void setupingredientListtoString(List<IngredientModel> ingredientList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < ingredientList.size(); j++) {
            String a = " \u273B " + ingredientList.get(j).getIngredient()
                    + " (" + ingredientList.get(j).getQuantity().toString()
                    + " " + ingredientList.get(j).getMeasure() + ").\n";
            stringBuilder.append(a);
        }
        String finalString = stringBuilder.toString();
        textView.setText(finalString);
    }
}

