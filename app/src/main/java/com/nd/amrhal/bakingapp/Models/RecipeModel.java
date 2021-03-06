package com.nd.amrhal.bakingapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeModel implements Parcelable {

    public RecipeModel(int id, String name, List<IngredientModel> ingredients, List<StepModel> steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    @SerializedName("id")
    private int id;

    @SerializedName("name")

    private String name;

    @SerializedName("ingredients")
    private List<IngredientModel> ingredients;

    @SerializedName("steps")
    private List<StepModel> steps;

    @SerializedName("servings")
    private int servings;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("image")
    private String image;


    protected RecipeModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.createTypedArrayList(IngredientModel.CREATOR);
        steps = in.createTypedArrayList(StepModel.CREATOR);
        servings = in.readInt();
    }

    public static final Creator<RecipeModel> CREATOR = new Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel in) {
            return new RecipeModel(in);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(List<StepModel> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
        dest.writeInt(servings);
    }


//    @SerializedName("id")
//    String mId;
//    @SerializedName("name")
//    String mName;
//
//    String servings;
//
//    public RecipeModel(String id, String name, String servings) {
//        this.mId = id;
//        this.mName = name;
//        this.servings = servings;
//    }
//
//    public String getId() {
//        return mId;
//    }
//
//    public String getName() {
//        return mName;
//    }
//
//    public String getServings() {
//        return servings;
//    }
}
