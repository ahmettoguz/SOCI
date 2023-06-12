package com.soci.soci.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.soci.soci.Business.MainSys;
import com.soci.soci.Model.Event;
import com.soci.soci.Model.Person;
import com.soci.soci.databinding.ActivityDisplayEventBinding;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;


public class DisplayEventActivity extends AppCompatActivity {
    ActivityDisplayEventBinding binding;

    Person current_Person;

    Event current_Event;

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

        // get required objects from intent
        Intent receivedIntent = getIntent();
        current_Person = MainSys.getPersonById(receivedIntent.getIntExtra("person_Id", -1));
        current_Event = MainSys.getEventFromId(receivedIntent.getIntExtra("event_Id", -1));
        MainSys.msg(this, current_Event.getName());


    }
}