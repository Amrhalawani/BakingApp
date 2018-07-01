package com.nd.amrhal.bakingapp;


import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepDetailFragment extends Fragment {
    TextView textView;

    public RecipeStepDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);
        textView = view.findViewById(R.id.textview_StepDetailFragment);


            String asd = String.valueOf(getArguments() != null ? getArguments().getInt("pos") : 0);
            Toast.makeText(getActivity(), "from Step f pos= " + asd, Toast.LENGTH_SHORT).show();

            return view;
        }


    protected void displayReceivedData(String message)
    {
        textView.setText("Data received: "+message);
    }

}
