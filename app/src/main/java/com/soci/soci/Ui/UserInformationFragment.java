package com.soci.soci.Ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.soci.soci.databinding.FragmentEventsBinding;
import com.soci.soci.databinding.FragmentUserInformationBinding;


public class UserInformationFragment extends Fragment {

    Context ctx;
    int current_Person_id;

    public UserInformationFragment(Context ctx, int current_Person_id) {
        this.ctx = ctx;
        this.current_Person_id = current_Person_id;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentUserInformationBinding binding = FragmentUserInformationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.userInfoBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "working." + current_Person_id, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}