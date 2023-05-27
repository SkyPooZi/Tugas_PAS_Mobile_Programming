package com.example.tugaspas_zidane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailFoodPage extends AppCompatActivity {

    Intent i;
    FoodModel foodModel;
    TextView tvFoodName, tvFoodDescription;
    ImageView ivFoodImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food_page);
        i = getIntent();
        foodModel = (FoodModel) i.getParcelableExtra("myFood");
        System.out.println("my team name : "+ foodModel.getFoodName());
        tvFoodName = findViewById(R.id.tvfoodname);
        tvFoodDescription = findViewById(R.id.tvfooddescription);
        ivFoodImage = findViewById(R.id.ivfoodimage);
        tvFoodName.setText(foodModel.getFoodName());
        tvFoodDescription.setText(foodModel.getFoodDescription());
        Glide.with(this).load(foodModel.getFoodImage()).into(ivFoodImage);

    }
}