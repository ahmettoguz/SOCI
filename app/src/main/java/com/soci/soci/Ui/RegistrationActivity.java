package com.soci.soci.Ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.view.WindowManager;
import android.widget.RadioButton;

import com.soci.soci.Business.MainSys;
import com.soci.soci.Database.DatabaseHelper;
import com.soci.soci.Database.Person_Table;
import com.soci.soci.Model.Person;
import com.soci.soci.databinding.ActivityRegistrationBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RegistrationActivity extends AppCompatActivity {
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // hide title bar
        getSupportActionBar().hide();
        setContentView(view);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // lock orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // register button event
        binding.registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> result = registerOperation();

                if (result.get("success").equalsIgnoreCase("false")) {
                    // display error message
                    MainSys.msg(RegistrationActivity.this, result.get("message"));
                } else {

                    // add to database
                    DatabaseHelper dbHelper;
                    dbHelper = new DatabaseHelper(RegistrationActivity.this);
                    if (Person_Table.insert(dbHelper, result.get("name"), result.get("surname"), result.get("email"), result.get("password"), result.get("phone"), result.get("gender")) != -1) {
                        MainSys.msg(RegistrationActivity.this, "Registration done successfully.");

                        // update data
                        MainSys.prepareDatabaseData(dbHelper);
                    } else
                        MainSys.msg(RegistrationActivity.this, "Registration done successfully.");

                    finish();
                }
            }
        });
    }

    private Map registerOperation() {
        // get the values
        String input_Name = binding.registerEtName.getText().toString();
        String input_Surname = binding.registerEtSurname.getText().toString();
        String input_Email = binding.registerEtMail.getText().toString();
        String input_Phone = binding.registerEtPhone.getText().toString();
        String input_Password = binding.registerEtPassword.getText().toString();
        String input_Gender = null;
        int selectedRadioButtonId = binding.registerRgGender.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            input_Gender = selectedRadioButton.getText().toString();
        }

        // create output
        Map<String, String> output = new HashMap<>();
        output.put("success", "false");


        if (input_Name.length() == 0) {
            output.put("message", "Name is required!");
            return output;
        }
        if (input_Surname.length() == 0) {
            output.put("message", "Surname is required!");
            return output;
        }

        if (input_Gender == null) {
            output.put("message", "Gender is required!");
            return output;
        }

        if (input_Email.length() == 0) {
            output.put("message", "Email is required!");
            return output;
        }

        if (input_Password.length() == 0) {
            output.put("message", "Password is required!");
            return output;
        }

        for (Person p : MainSys.people) {
            if (p.getEmail().equalsIgnoreCase(input_Email)) {
                output.put("message", "This email in use!");
                return output;
            }
        }

        if (input_Phone.length() != 0 && input_Phone.length() != 11) {
            output.put("message", "Phone should be 11 digit!");
            return output;
        }

        if (MainSys.containsNonDigit(input_Phone)) {
            output.put("message", "Phone should just include digits!");
            return output;
        }

        if (input_Password.length() < 4) {
            output.put("message", "Password length is less than 4!");
            return output;
        }

        output.put("success", "true");
        output.put("name", input_Name);
        output.put("surname", input_Surname);
        output.put("gender", input_Gender);
        output.put("email", input_Email);
        output.put("phone", input_Phone);
        output.put("password", input_Password);

        return output;
    }
}