package com.soci.soci.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.pm.ActivityInfo;
import android.view.WindowManager;
import android.widget.Toast;

import com.soci.soci.Business.MainSys;
import com.soci.soci.MainActivity;
import com.soci.soci.Model.Person;
import com.soci.soci.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // hide title bar
        getSupportActionBar().hide();
        setContentView(view);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // lock orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // prepare data - will change with database
        MainSys.prepareData();

        // fill data not to put extra effort
        fillForm();

        // login btn event
        binding.LoginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> result = loginOperation();

                if (result.get("success").equalsIgnoreCase("true")) {
                    Intent sendIntent = new Intent(LoginActivity.this, MainActivity.class);
                    sendIntent.putExtra("person_id", Integer.parseInt(result.get("id")));
                    startActivity(sendIntent);
                    finish();
                } else {
                    MainSys.msg(LoginActivity.this, result.get("message"));
                }
            }
        });

        // register text event
        binding.loginTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(sendIntent);
            }
        });
    }

    private void fillForm() {
        binding.loginEtEmail.setText("ahmet@hotmail.com");
        binding.loginEtPassword.setText("ahmet123");
    }

    private Map loginOperation() {
        String input_Email = binding.loginEtEmail.getText().toString();
        String input_Password = binding.loginEtPassword.getText().toString();

        Map<String, String> output = new HashMap<>();
        output.put("success", "false");

        if (input_Email.length() == 0) {
            output.put("message", "Email is required!");
            return output;
        }

        if (input_Password.length() == 0) {
            output.put("message", "Password is required!");
            return output;
        }

        if (input_Password.length() < 4) {
            output.put("message", "Password length is less than 4!");
            return output;
        }

        for (Person p : MainSys.people) {
            if (p.getEmail().equalsIgnoreCase(input_Email) && p.getPassword().equalsIgnoreCase(input_Password)) {
                output.put("success", "true");
                output.put("id", p.getId() + "");
                return output;
            }
        }

        output.put("message", "Login failed.");
        return output;
    }
}