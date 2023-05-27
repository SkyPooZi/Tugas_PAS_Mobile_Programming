package com.example.tugaspas_zidane;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    //list widget yang akan dikenakan aksi
    EditText txtUsername, txtPassword, txtFullName, txtEmail;
    Button btnRegister;
    ProgressBar pbLadingBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUsername = findViewById(R.id.txtUsername2);
        txtPassword = findViewById(R.id.txtPassword2);
        txtFullName = findViewById(R.id.txtFullName2);
        txtEmail = findViewById(R.id.txtEmail2);
        pbLadingBar = findViewById(R.id.pbloadingBar2);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                String fullname = txtFullName.getText().toString();
                String email = txtEmail.getText().toString();
                pbLadingBar.setVisibility(View.VISIBLE);
                btnRegister.setEnabled(false);

                // hit api login
                AndroidNetworking.post("https://mediadwi.com/api/latihan/register-user")
                        .addBodyParameter("username", username)
                        .addBodyParameter("password", password)
                        .addBodyParameter("full_name", password)
                        .addBodyParameter("email", password)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle successful response
                                Log.d("sukses login", "onResponse: " + response.toString());
                                try {
                                    boolean status = response.getBoolean("status");
                                    String message = response.getString("message");
                                    if (status) {
                                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Register.this, ListFoodNameActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                pbLadingBar.setVisibility(View.GONE);
                                btnRegister.setEnabled(true);
                            }

                            @Override
                            public void onError(ANError anError) {
                                // Handle error
                            }
                        });

            }
        });
    }
}