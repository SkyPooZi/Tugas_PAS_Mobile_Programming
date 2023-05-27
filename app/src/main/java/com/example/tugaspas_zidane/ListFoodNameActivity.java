package com.example.tugaspas_zidane;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListFoodNameActivity extends AppCompatActivity implements FoodAdapter.FoodAdapterListener{

    public static final String SHARED_PREFS = "shared_prefs";

    public static final String EMAIL_KEY = "email_key";

    public static final String PASSWORD_KEY = "password_key";

    SharedPreferences sharedpreferences;

    RecyclerView rvFoodName;
    ArrayList<FoodModel> listDataFoods;
    private FoodAdapter adapterListFood;
    ProgressBar pbloadingteam;

    public void getFoodOnline(){
        String url = "https://www.themealdb.com/api/json/v1/1/categories.php";
        AndroidNetworking.get(url)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        Log.d("success ", "onResponse: "+jsonObject.toString());
                        try {
                            JSONArray jsonArrayFood = jsonObject.getJSONArray("categories");
                            for (int i = 0; i < jsonArrayFood.length(); i++) {
                                FoodModel myFood = new FoodModel();
                                JSONObject jsonTeam = jsonArrayFood.getJSONObject(i);
                                myFood.setFoodName(jsonTeam.getString("strCategory"));
                                myFood.setFoodImage(jsonTeam.getString("strCategoryThumb"));
                                myFood.setFoodDescription(jsonTeam.getString("strCategoryDescription"));
                                listDataFoods.add(myFood);
                            }
                            rvFoodName = findViewById(R.id.rvkontakname);
                            adapterListFood = new FoodAdapter(getApplicationContext(), listDataFoods, ListFoodNameActivity.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            rvFoodName.setHasFixedSize(true);
                            rvFoodName.setLayoutManager(mLayoutManager);
                            rvFoodName.setAdapter(adapterListFood);

                            pbloadingteam.setVisibility(View.GONE);
                            rvFoodName.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("failed ", "onError: "+anError.toString());
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food_name);
        listDataFoods = new ArrayList<>();
        pbloadingteam = findViewById(R.id.pbloadingteam);
        getFoodOnline();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // Handle settings action
            sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            sharedpreferences.edit().remove(EMAIL_KEY).remove(PASSWORD_KEY).apply();
            startActivity(new Intent(ListFoodNameActivity.this, SplashScreen.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFoodSelected(FoodModel foodModel) {
        // move to another page
        Intent intent = new Intent(ListFoodNameActivity.this, DetailFoodPage.class);
        intent.putExtra("myFood", foodModel);
        startActivity(intent);
    }

    @Override
    public void onDeleteClickListener(FoodModel foodModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete")
                .setMessage("Are you sure you want to delete this ID: " + foodModel.getFoodName())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Perform any action on OK button click
                        deleteItem(foodModel);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Perform any action on Cancel button click
                    }
                })
                .show();
    }


    private void deleteItem(FoodModel foodModel) {
        // Implement the deletion logic here
        // For example, remove the item from the list and notify the adapter
        int position = listDataFoods.indexOf(foodModel);
        if (position != -1) {
            listDataFoods.remove(position);
            adapterListFood.notifyItemRemoved(position);
        }
    }
}