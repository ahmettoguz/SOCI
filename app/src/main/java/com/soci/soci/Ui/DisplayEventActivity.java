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

        // set components
        binding.eventTVName.setText(current_Event.getName());
        binding.eventTVDescription.setText(binding.eventTVDescription.getText() + current_Event.getDescription());
        binding.eventTVStartDate.setText(current_Event.getStart_Date());
        binding.eventTVEndDate.setText(current_Event.getEnd_Date());
        binding.eventTVPlace.setText(current_Event.getLocation());

        int participated_People_Count = 0;
        binding.eventTVQuota.setText(binding.eventTVQuota.getText().toString() + participated_People_Count + "/" + current_Event.getMax_Participant());

        String imgName = MainSys.getImgNameFromCategory(current_Event.getCategory());
        int imgId = MainSys.convertImageNameToId(this, imgName);
        binding.eventIvCategory.setImageResource(imgId);
    }
}