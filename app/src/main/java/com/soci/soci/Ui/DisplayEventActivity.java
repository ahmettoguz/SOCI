package com.soci.soci.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.soci.soci.Business.MainSys;
import com.soci.soci.Model.Event;
import com.soci.soci.Model.Person;
import com.soci.soci.R;
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

    final int OWNER = 1;
    final int PARTICIPATOR = 2;
    final int NORMAL = 3;


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

        // specify user role
        int person_Role = NORMAL;
        for (Integer owned_id : current_Person.getCreated_Events()) {
            if (owned_id == current_Event.getId())
                person_Role = OWNER;
        }

        for (Integer participated_id : current_Person.getParticipated_Events()) {
            if (participated_id == current_Event.getId())
                person_Role = PARTICIPATOR;
        }

        // count participated user count
        int participated_People_Count = 0;

        for (Person p : MainSys.people) {
            for (Integer p_id : p.getParticipated_Events()) {
                if (p_id == current_Event.getId())
                    participated_People_Count++;
            }
            for (Integer p_id : p.getCreated_Events()) {
                if (p_id == current_Event.getId())
                    participated_People_Count++;
            }
        }


        // set components
        binding.eventTVName.setText(current_Event.getName());
        binding.eventTVDescription.setText(binding.eventTVDescription.getText() + current_Event.getDescription());
        binding.eventTVStartDate.setText(current_Event.getStart_Date());
        binding.eventTVEndDate.setText(current_Event.getEnd_Date());
        binding.eventTVPlace.setText(current_Event.getLocation());

        // quota related
        String quota = current_Event.getMax_Participant() == -1 ? "No Limit" : current_Event.getMax_Participant() + "";

        // set quota
        binding.eventTVQuota.setText(binding.eventTVQuota.getText().toString() + participated_People_Count + "/" + quota);

        // set quota color
        if (person_Role == OWNER) {
            binding.eventTVQuota.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Orange));
        } else if (person_Role == PARTICIPATOR) {
            binding.eventTVQuota.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.BlueViolet));
        } else {
            if (quota.equalsIgnoreCase("No Limit") || Integer.parseInt(quota) != participated_People_Count) {
                binding.eventTVQuota.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Green));
            } else {
                binding.eventTVQuota.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Red));
            }
        }

        // set image
        String imgName = MainSys.getImgNameFromCategory(current_Event.getCategory());
        int imgId = MainSys.convertImageNameToId(this, imgName);
        binding.eventIvCategory.setImageResource(imgId);

        // set button image
        int redColor = ContextCompat.getColor(this, R.color.Red);
        int greenColor = ContextCompat.getColor(this, R.color.Green);
        int orangeColor = ContextCompat.getColor(this, R.color.Orange);

        if (binding.eventTVQuota.getCurrentTextColor() == redColor || binding.eventTVQuota.getCurrentTextColor() == greenColor) {
            imgName = "action_add_event";
        } else {
            imgName = "action_remove_event";
        }
        imgId = MainSys.convertImageNameToId(this, imgName);
        binding.eventBtnJoinLeave.setImageResource(imgId);

        final int final_Person_Role = person_Role;

        // update btn event
        binding.eventBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (final_Person_Role == OWNER) {
                    // update eklenicek
                } else {
                    MainSys.msg(DisplayEventActivity.this, "Just owner can update event!");
                }
            }
        });

        // join leave button event
        binding.eventBtnJoinLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (final_Person_Role == OWNER) {
                    MainSys.msg(DisplayEventActivity.this, "Owner cannot leave the event!");
                } else if (final_Person_Role == PARTICIPATOR) {
//                    ... ekle çıkar gelicek
                } else if (final_Person_Role == NORMAL && binding.eventTVQuota.getCurrentTextColor() == greenColor) {
//                    add
                }
            }
        });

        // delete button event
        binding.eventBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (final_Person_Role == OWNER) {
                    MainSys.events.remove(current_Event);
                    MainSys.msg(DisplayEventActivity.this, "Event is deleted.");

                    finish();
                } else {
                    MainSys.msg(DisplayEventActivity.this, "Just owner can delete event!");
                }
            }
        });


        // click events
//        binding.eventBtnJoinLeave
    }
}