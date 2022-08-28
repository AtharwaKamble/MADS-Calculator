package com.example.madscalculator.Authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madscalculator.Calculator.CalculatorActivity;
import com.example.madscalculator.R;

public class LoginActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_login;
    SharedPreferences sharedPreferences;
    private final String fileName = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkLogin();


        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);

        btn_login = findViewById(R.id.login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_username.getText().toString().equals("test@test.com") && et_password.getText().toString().equals("test123")){

                    sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", et_username.getText().toString());
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, CalculatorActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(LoginActivity.this, "incorrect details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
        String check = sharedPreferences.getString("username", "");
        if (check.equals("test@test.com")){
            Toast.makeText(LoginActivity.this, "Already Loggedin", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, CalculatorActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
