package com.soci.soci.Ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        binding.userEventBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "pressed." + current_Person_id, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}