package com.soci.soci.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.soci.soci.Business.MainSys;
import com.soci.soci.Model.Person;
import com.soci.soci.R;
import com.soci.soci.databinding.ActivityEventAddBinding;
import com.soci.soci.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class Event_Add_Activity extends AppCompatActivity {
    ActivityEventAddBinding binding;
    Person current_Person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEventAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // hide title bar
        getSupportActionBar().hide();
        setContentView(view);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // lock orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // get current user
        Intent receivedIntent = getIntent();
        int current_Person_id = receivedIntent.getIntExtra("person_id", -1);
        current_Person = MainSys.getPersonById(current_Person_id);

        // fill spinner
        String tempCategoryList[] = getResources().getStringArray(R.array.categories);
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Select category");
        for (int i = 0; i < tempCategoryList.length; i++) {
            if (i != 0)
                categoryList.add(tempCategoryList[i]);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categoryList);
        binding.addEventSpCategories.setAdapter(adapter);

        // automatically fill the form
        fillForm();

        MainSys.msg(this, current_Person.getName());
    }

    private void fillForm() {


    }
}