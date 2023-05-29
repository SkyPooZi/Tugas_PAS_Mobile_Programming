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
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

 public class MainActivity extends AppCompatActivity {

     private GoogleSignInClient mGoogleSignInClient;

     private static final int RC_SIGN_IN = 9001;

    public static final String SHARED_PREFS = "shared_prefs";

    public static final String EMAIL_KEY = "email_key";

    public static final String PASSWORD_KEY = "password_key";

    SharedPreferences sharedpreferences;
    String email, password;

    //list widget yang akan dikenakan aksi
    EditText txtUsername, txtPassword;
    Button btnLogin, btnSignIn;
    ProgressBar pbLadingBar;
    TextView tvRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // konekkan semua komponen dengan xml nya
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        pbLadingBar = findViewById(R.id.pbloadingBar);
        tvRegister = findViewById(R.id.tvRegister);
        btnSignIn = findViewById(R.id.btnSignIn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,RC_SIGN_IN);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                pbLadingBar.setVisibility(View.VISIBLE);
                btnLogin.setEnabled(false);

                // hit api login
                AndroidNetworking.post("https://mediadwi.com/api/latihan/login")
                        .addBodyParameter("username", username)
                        .addBodyParameter("password", password)
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
                                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                        // atau silahkan buat dialog
                                        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putString(EMAIL_KEY, username.toString());
                                        editor.putString(PASSWORD_KEY, "");

                                        // to save our data with key and value.
                                        editor.apply();
                                        startActivity(new Intent(MainActivity.this, ListFoodNameActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                pbLadingBar.setVisibility(View.GONE);
                                btnLogin.setEnabled(true);
                            }

                            @Override
                            public void onError(ANError anError) {
                                // Handle error
                            }
                        });
                }
            });
        }
         @Override
         public void onActivityResult(int requestCode, int resultCode, Intent data) {
             super.onActivityResult(requestCode, resultCode, data);
             if (requestCode == RC_SIGN_IN) {
                 Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                 try {
                     GoogleSignInAccount account = task.getResult(ApiException.class);
                     sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                     SharedPreferences.Editor editor = sharedpreferences.edit();
                     editor.putString(EMAIL_KEY,account.getId());
                     editor.putString(PASSWORD_KEY, "");
                     // to save our data with key and value.
                     editor.apply();

                     startActivity(new Intent(MainActivity.this, SplashScreen.class));
                     finish();
                     // Proses sign-in berhasil, Anda dapat menyimpan data akun menggunakan Shared Preferences di sini
                 } catch (ApiException e) {
                     // Sign-in gagal, Anda dapat menangani kesalahan di sini
                 }
             }
         }
    }