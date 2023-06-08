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

    public EventsFragment(Context ctx) {
        this.ctx = ctx;
        // Required empty public constructor
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


        binding.eventsRegistActBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                intent = new Intent(ctx, InformationActivity.class);
                startActivity(intent);
            }
        });

        binding.eventsBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;

                intent = new Intent(ctx, LoginActivity.class);
                startActivity(intent);
            }
        });

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