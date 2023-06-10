package com.soci.soci.Ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.soci.soci.Adapter.EventsAdapter;
import com.soci.soci.Business.MainSys;
import com.soci.soci.Model.Event;
import com.soci.soci.Model.Person;
import com.soci.soci.databinding.FragmentEventsBinding;

public class EventsFragment extends Fragment  implements EventsAdapter.EventsAdapterBehavior{

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

        // get current person
        Person current_Person = MainSys.getPersonById(current_Person_id);

        // recycler view related
        // * place items one by one
        LinearLayoutManager layoutManager = new LinearLayoutManager(ctx);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.eventsRvEvents.setLayoutManager(layoutManager);

        //// * place item without considering rectangle sizes like pinterest post blog
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(staggeredGridLayoutManager);

        //// * place items with grid system
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //recyclerView.setLayoutManager(gridLayoutManager);

        // fill the RecyclerView
        EventsAdapter adapter = new EventsAdapter(ctx, MainSys.getEventsAsArrayListFromCategory("all"), current_Person);
        binding.eventsRvEvents.setAdapter(adapter);

        //// if new element is added notify this change to the recycler view
        //Button btnAdd = findViewById(R.id.btn_Main_Add);
        //btnAdd.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Social s = new Social("ahmooo", R.drawable.ic_launcher_foreground);
        //        MainSys.getSocials().add(s);
        //        adapter.notifyDataSetChanged();
        //    }
        //});

        // recycler view related end

        binding.eventsBtnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "Add clicked", Toast.LENGTH_SHORT).show();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void displayEventItem(Event event) {
        MainSys.msg(ctx, event.getName());
    }


//
//    public void createShowDialog(Event organization) {
//        Dialog customDialog = new Dialog(MainActivity.this);
//        customDialog.setContentView(R.layout.dialog);
//
//        TextView tv = customDialog.findViewById(R.id.tvDialogName);
//        Button btnClose = customDialog.findViewById(R.id.btnDialogClose);
//
//        tv.setText(organization.getCompanyName());
//
//        btnClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                customDialog.dismiss();
//            }
//        });
//
//        customDialog.show();
//    }
}