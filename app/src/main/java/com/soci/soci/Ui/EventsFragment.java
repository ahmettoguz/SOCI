package com.soci.soci.Ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.soci.soci.databinding.FragmentEventsBinding;

public class EventsFragment extends Fragment {

    Context ctx;
    int current_Person_id;

    EventsFragmentInterface fragmentInterfaceListener;

    public EventsFragment(Context ctx, int current_Person_id) {
        this.ctx = ctx;
        this.current_Person_id = current_Person_id;

        if (ctx instanceof EventsFragmentInterface)
            fragmentInterfaceListener = (EventsFragmentInterface) ctx;
    }

    interface EventsFragmentInterface {
        public void performEventsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentEventsBinding binding = FragmentEventsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        binding.eventsBtnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "Add clicked", Toast.LENGTH_SHORT).show();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}