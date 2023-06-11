package com.soci.soci.Ui;

import android.app.Dialog;
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

import com.soci.soci.API.JsonPlaceHolderApi;
import com.soci.soci.Adapter.EventsAdapter;
import com.soci.soci.Business.MainSys;
import com.soci.soci.Model.Person;
import com.soci.soci.Model.Weather;
import com.soci.soci.databinding.FragmentEventsBinding;
import com.soci.soci.databinding.FragmentUserEventBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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

        binding.userEventBtnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(JsonPlaceHolderApi.WEATHER_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                Call<Weather> call = jsonPlaceHolderApi.getWeather();

                call.enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(ctx, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.d("RESPONSE", response.body().toString());

                        Weather weather = response.body();

                        if (weather != null) {
                            Log.d("ahmet weather", weather.toString());
                        } else
                            Toast.makeText(ctx, "Not Found", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        Log.d("RESPONSE", "Failure" + t.getMessage());
                    }
                });
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

//    public void createShowDialog() {
//        Dialog customDialog = new Dialog(ctx);
//        customDialog.setContentView(R.layout.my_custom_dialog);
//
//        TextView tv = customDialog.findViewById(R.id.tv_dialog);
//        ImageView iv = customDialog.findViewById(R.id.iv_dialog);
//        Button btnClose = customDialog.findViewById(R.id.btn_dialog_close);
//
//        tv.setText("ahmet");
//        iv.setImageResource(R.drawable.ahmet1);
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