package com.nd.amrhal.bakingapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nd.amrhal.bakingapp.Step.StepModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amr Halawani on 26/06/2018.
 */

public class StepsRecyclerAdaptor extends RecyclerView.Adapter<StepsRecyclerAdaptor.myViewholder> {

    private static final String TAG = "TAG";
    List<StepModel> list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    OnItemClickListener onItemClickListener;

    public StepsRecyclerAdaptor(Context context) {
        this.context = context;
    }


    public void updateData(List newList) {
        this.list = newList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_step, parent, false);

        StepsRecyclerAdaptor.myViewholder myViewholder = new StepsRecyclerAdaptor.myViewholder(view);

        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, final int position) {
        StepModel SModel = list.get(position);
        holder.step.setText(SModel.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }

    class myViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView step;
        ConstraintLayout constraintLayout;
        LinearLayout linearLayout;

        public myViewholder(View itemView) {
            super(itemView);
            Log.e(TAG, "myViewholder: ");
            step = itemView.findViewById(R.id.item_step_name);

//            constraintLayout = itemView.findViewById(R.id.constrainLayout);
//            linearLayout = itemView.findViewById(R.id.linearLayout);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }


}
