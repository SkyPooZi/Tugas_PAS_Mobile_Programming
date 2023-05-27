package com.example.tugaspas_zidane;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodModel implements Parcelable {

    private String foodName;
    private String foodDescription;
    private String foodImage;

    protected FoodModel(Parcel in) {
        foodName = in.readString();
        foodDescription = in.readString();
        foodImage = in.readString();
    }

    FoodModel(){

    }

    public static final Creator<FoodModel> CREATOR = new Creator<FoodModel>() {
        @Override
        public FoodModel createFromParcel(Parcel in) {
            return new FoodModel(in);
        }

        @Override
        public FoodModel[] newArray(int size) {
            return new FoodModel[size];
        }
    };

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foodName);
        parcel.writeString(foodDescription);
        parcel.writeString(foodImage);
    }
}
