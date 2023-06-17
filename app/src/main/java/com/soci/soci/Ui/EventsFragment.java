package com.soci.soci.Ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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

import org.w3c.dom.Text;

public class EventsFragment extends Fragment {

    Context ctx;
    int current_Person_id;
    FragmentEventsBinding binding;
    Person current_Person;
    EventsFragmentInterface interfaceListener;

    public interface EventsFragmentInterface {
        public void eventsFragmentBehavior();
    }

    public EventsFragment(Context ctx, int current_Person_id) {
        this.ctx = ctx;
        this.current_Person_id = current_Person_id;

        if (ctx instanceof EventsFragmentInterface)
            interfaceListener = (EventsFragmentInterface) ctx;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // get current person
        current_Person = MainSys.getPersonById(current_Person_id);

        // recycler view
        fillRecyclerView("all");

        binding.eventsBtnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "Add clicked", Toast.LENGTH_SHORT).show();
                interfaceListener.eventsFragmentBehavior();
            }
        });

        binding.eventsSpCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                view = (TextView) view;
                fillRecyclerView(((TextView) view).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void fillRecyclerView(String category) {
        // recycler view related
        LinearLayoutManager layoutManager = new LinearLayoutManager(ctx);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.eventsRvEvents.setLayoutManager(layoutManager);
        // fill the RecyclerView
        current_Person = MainSys.getPersonById(current_Person_id);
        EventsAdapter adapter = new EventsAdapter(ctx, "eventsFragment", MainSys.getEventsAsArrayListFromCategory(category), current_Person);
        binding.eventsRvEvents.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // recycler view related end
    }

    public void updateEventsFragment() {
        String category = binding.eventsSpCategories.getSelectedItem().toString();
        fillRecyclerView(category);
    }
}