package com.soci.soci.Ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.soci.soci.Adapter.EventsAdapter;
import com.soci.soci.Business.MainSys;
import com.soci.soci.Model.Person;
import com.soci.soci.databinding.FragmentEventsBinding;
import com.soci.soci.databinding.FragmentUserEventBinding;

public class UserEventFragment extends Fragment {

    Context ctx;
    int current_Person_id;

    public UserEventFragment(Context ctx, int current_Person_id) {
        this.ctx = ctx;
        this.current_Person_id = current_Person_id;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentUserEventBinding binding = FragmentUserEventBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // get current person
        Person current_Person = MainSys.getPersonById(current_Person_id);

        // recycler view
        fillRecyclerView(binding, current_Person, "all");

        binding.userEventSpFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                view = (TextView) view;
                fillRecyclerView(binding, current_Person, ((TextView) view).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void fillRecyclerView(FragmentUserEventBinding binding, Person current_Person, String participation) {
        // recycler view related
        LinearLayoutManager layoutManager = new LinearLayoutManager(ctx);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.userEventRvEvents.setLayoutManager(layoutManager);
        // fill the RecyclerView
        EventsAdapter adapter = new EventsAdapter(ctx, MainSys.getEventsAsArrayListFromParticipation(participation, current_Person), current_Person);
        binding.userEventRvEvents.setAdapter(adapter);
        // recycler view related end
    }
}