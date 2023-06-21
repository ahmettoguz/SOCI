package com.soci.soci.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.soci.soci.Business.MainSys;
import com.soci.soci.Database.DatabaseHelper;
import com.soci.soci.Database.Event_Table;
import com.soci.soci.Database.Person_Event_Owner_Table;
import com.soci.soci.Model.Event;
import com.soci.soci.Model.Person;
import com.soci.soci.R;
import com.soci.soci.databinding.ActivityEventAddBinding;
import com.soci.soci.databinding.ActivityEventUpdateBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Event_Update_Activity extends AppCompatActivity {
    ActivityEventUpdateBinding binding;
    Person current_Person;
    Event current_Event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEventUpdateBinding.inflate(getLayoutInflater());
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
        int current_Event_id = receivedIntent.getIntExtra("event_id", -1);
        current_Person = MainSys.getPersonById(current_Person_id);
        current_Event = MainSys.getEventFromId(current_Event_id);

        // fill spinner
        String tempCategoryList[] = getResources().getStringArray(R.array.categories);
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Select category");
        for (int i = 0; i < tempCategoryList.length; i++) {
            if (i != 0)
                categoryList.add(tempCategoryList[i]);
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categoryList);
        binding.updateEventSpCategories.setAdapter(adapter);
        String imgName = "category_all";
        int img_ID = MainSys.convertImageNameToId(Event_Update_Activity.this, imgName);
        binding.updateEventIvCategoryImg.setImageResource(img_ID);

        // spinner event
        binding.updateEventSpCategories.setSelection(0, false);
        binding.updateEventSpCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = ((TextView) view).getText().toString();
                if (!selectedItem.equalsIgnoreCase("Select category")) {
                    Log.d("ahmet", selectedItem);
                    String imgName = MainSys.getImgNameFromCategory(selectedItem);
                    int img_ID = MainSys.convertImageNameToId(Event_Update_Activity.this, imgName);
                    binding.updateEventIvCategoryImg.setImageResource(img_ID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // automatically fill the form
        fillForm();

        // cancel button event
        binding.udpateEventBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // approve button event
//        binding.udpateEventBtnApprove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Map<String, String> result = checkErrors();
//
//                if (result.get("success").equalsIgnoreCase("true")) {
//
//                    // get the input
//                    int id, max_Participant;
//                    String name, category, description, location, start_Date, end_Date;
//                    name = binding.addEventEtName.getText().toString();
//                    description = binding.addEventEtDescription.getText().toString();
//                    location = binding.addEventEtPlace.getText().toString();
//                    start_Date = binding.addEventEtStartDate.getText().toString();
//                    end_Date = binding.addEventEtEndDate.getText().toString();
//                    max_Participant = binding.addEventEtQuota.getText().toString().equalsIgnoreCase("") ? -1 : Integer.parseInt(binding.addEventEtQuota.getText().toString());
//                    category = ((TextView) binding.addEventSpCategories.getSelectedView()).getText().toString();
//
//                    // database insertion operation for the event
//                    DatabaseHelper dbHelper;
//                    dbHelper = new DatabaseHelper(Event_Add_Activity.this);
//
//                    int insertedEventId = Event_Table.insert(dbHelper, max_Participant, name, category, description, location, start_Date, end_Date);
//                    if (insertedEventId != -1) {
//                        if (Person_Event_Owner_Table.insert(dbHelper, current_Person_id + "", insertedEventId + "") != -1) {
//                            MainSys.msg(Event_Add_Activity.this, "Event is added successfully");
//
//                            // update data
//                            MainSys.prepareDatabaseData(dbHelper);
//
//                            // return intent to update recycler view
//                            Intent returnIntent = new Intent();
//                            returnIntent.putExtra("success", "true");
//                            setResult(RESULT_OK, returnIntent);
//                            finish();
//                        } else {
//                            MainSys.msg(Event_Add_Activity.this, "Database insertion error! ");
//                        }
//                    } else {
//                        MainSys.msg(Event_Add_Activity.this, "Database insertion error! ");
//                    }
//                } else {
//                    MainSys.msg(Event_Add_Activity.this, result.get("message"));
//                }
//            }
//        });
    }

    private void fillForm() {
        String maxParticipant = (current_Event.getMax_Participant() + "").equalsIgnoreCase("-1") ? "" : current_Event.getMax_Participant() + "";

        // get spinner position
        String category = current_Event.getCategory();
        int categoryPosition = -1;
        String tempCategoryList[] = getResources().getStringArray(R.array.categories);
        for (int i = 0; i < tempCategoryList.length; i++) {
            if (tempCategoryList[i].equalsIgnoreCase(category))
                categoryPosition = i;
        }

        binding.updateEventSpCategories.setSelection(categoryPosition, false);
        binding.updateEventEtName.setText(current_Event.getName());
        binding.updateEventEtDescription.setText(current_Event.getDescription());
        binding.updateEventEtPlace.setText(current_Event.getLocation());
        binding.updateEventEtQuota.setText(maxParticipant);
        binding.updateEventEtStartDate.setText(current_Event.getStart_Date());
        binding.updateEventEtEndDate.setText(current_Event.getEnd_Date());
    }

    private Map<String, String> checkErrors() {
        Map<String, String> result = new HashMap<>();
        result.put("success", "false");

        if (binding.updateEventSpCategories.getSelectedItem().toString().equalsIgnoreCase("Select category"))
            result.put("message", "Select category!");
        else if (binding.updateEventEtName.getText().toString().length() == 0)
            result.put("message", "Event name is empty!");
        else if (binding.updateEventEtDescription.getText().toString().length() == 0)
            result.put("message", "Description is empty!");
        else if (binding.updateEventEtPlace.getText().toString().length() == 0)
            result.put("message", "Place is empty!");
        else if (binding.updateEventEtStartDate.getText().toString().length() == 0)
            result.put("message", "Start date is empty!");
        else if (binding.updateEventEtEndDate.getText().toString().length() == 0)
            result.put("message", "End date is empty!");
        else
            result.put("success", "true");

        return result;
    }
}