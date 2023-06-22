package com.soci.soci.Ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soci.soci.Business.MainSys;
import com.soci.soci.Model.Person;
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

        // get current person
        Person current_Person = MainSys.getPersonById(current_Person_id);

        fillInformations(ctx, binding, current_Person);

        return view;
    }

    private void fillInformations(Context ctx, FragmentUserInformationBinding binding, Person current_Person) {
        binding.userInformationTvName.setText(binding.userInformationTvName.getText().toString() + current_Person.getName());
        binding.userInformationTvSurname.setText(binding.userInformationTvSurname.getText().toString() + current_Person.getSurname());
        binding.userInformationTvEmail.setText(binding.userInformationTvEmail.getText().toString() + current_Person.getEmail());
        binding.userInformationTvPhone.setText(binding.userInformationTvPhone.getText().toString() + current_Person.getPhone());

        String imgName = current_Person.getGender().equalsIgnoreCase("female") ? "female" : "male";
        int imgId = MainSys.convertImageNameToId(ctx, imgName);
        binding.userInformationIvGender.setImageResource(imgId);
    }
}