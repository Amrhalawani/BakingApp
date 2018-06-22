package com.nd.amrhal.bakingapp.Recipes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nd.amrhal.bakingapp.R;

import net.wujingchao.android.view.SimpleTagImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amr Halawani on 18/06/2018.
 */

public class RecipesRecyclerAdaptor extends RecyclerView.Adapter<RecipesRecyclerAdaptor.myViewholder> {

    private static final String TAG = "TAG";
    List<RecipeModel> list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    OnItemClickListener onItemClickListener;

    public RecipesRecyclerAdaptor(Context context) {
        this.context = context;
    }


    public void updateData(List newList) {
        this.list = newList;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recipe, parent, false);

        RecipesRecyclerAdaptor.myViewholder myViewholder = new RecipesRecyclerAdaptor.myViewholder(view);

        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {
        RecipeModel RModel = list.get(position);
        holder.RecipeName.setText(RModel.getName());
        holder.mSimpleTagImageViewServings.setTagText("Servings: "+ RModel.getServings()); // text Tag lib

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class myViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView RecipeName;
        SimpleTagImageView mSimpleTagImageViewServings;


        public myViewholder(View itemView) {
            super(itemView);
            Log.e(TAG, "myViewholder: ");
            RecipeName = itemView.findViewById(R.id.item_recipe_name);
            mSimpleTagImageViewServings = itemView.findViewById(R.id.item_servings_tag);

        }
        @Override
        public void onClick(View view) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }


}
