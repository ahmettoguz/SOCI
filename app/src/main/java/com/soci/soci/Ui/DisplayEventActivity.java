package com.soci.soci.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.soci.soci.databinding.ActivityDisplayEventBinding;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.WindowManager;


public class DisplayEventActivity extends AppCompatActivity {
    ActivityDisplayEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDisplayEventBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // hide title bar
        getSupportActionBar().hide();
        setContentView(view);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // lock orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }
}