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
    TextView tvSportName, tvLeague, tvCurrentSeason, tvDateFirstEvent, tvGender, tvCountry, tvDescriptionEN;
    ImageView ivBadgeImage, ivLogoImage, ivTrophyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food_page);
        i = getIntent();
        foodModel = (FoodModel) i.getParcelableExtra("myFood");
        System.out.println("my team name : "+ foodModel.getStrSport());
        tvSportName = findViewById(R.id.tvSportName);
        tvLeague = findViewById(R.id.tvLeague);
        tvCurrentSeason = findViewById(R.id.tvCurrentSeason);
        tvDateFirstEvent = findViewById(R.id.tvDateFirstEvent);
        tvGender = findViewById(R.id.tvGender);
        tvCountry = findViewById(R.id.tvCountry);
        tvDescriptionEN = findViewById(R.id.tvDescriptionEN);
        ivBadgeImage = findViewById(R.id.ivBadgeImage);
        ivLogoImage = findViewById(R.id.ivLogo);
        ivTrophyImage = findViewById(R.id.ivTrophy);

        tvSportName.setText(foodModel.getStrSport());
        tvLeague.setText("League : " + foodModel.getStrLeague());
        tvCurrentSeason.setText("Current Season : " + foodModel.getStrCurrentSeason());
        tvDateFirstEvent.setText("Date First Event : " + foodModel.getDateFirstEvent());
        tvGender.setText("Gender : " + foodModel.getStrGender());
        tvCountry.setText("Country : " + foodModel.getStrCountry());
        tvDescriptionEN.setText("DescriptionEN : " + foodModel.getStrDescriptionEN());
        Glide.with(this).load(foodModel.getStrBadge()).into(ivBadgeImage);
        Glide.with(this).load(foodModel.getStrLogo()).into(ivLogoImage);
        Glide.with(this).load(foodModel.getStrTrophy()).into(ivTrophyImage);

    }
}