package com.soci.soci;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.view.WindowManager;

import com.soci.soci.databinding.ActivityRegistrationBinding;


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


        // handle submit button to preview the entered data
//        binding.submitButton.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View v) {
//                // set the entered data to text preview
//                tvTextPreview.setText("You Entered : " + mEditText.getText().toString());
//            }
//        });
    }
}