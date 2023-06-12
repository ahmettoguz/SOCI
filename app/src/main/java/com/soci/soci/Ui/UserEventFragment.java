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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soci.soci.API.JsonPlaceHolderApi;
import com.soci.soci.Adapter.EventsAdapter;
import com.soci.soci.Business.MainSys;
import com.soci.soci.Model.Person;
import com.soci.soci.Model.Weather;
import com.soci.soci.R;
import com.soci.soci.databinding.FragmentUserEventBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    FragmentUserEventBinding binding;
    Person current_Person;
    UserEventInterface interfaceListener;
    View viewW;

    public interface UserEventInterface {
        public void userEventBehavior();
    }

    public UserEventFragment(Context ctx, int current_Person_id) {
        this.ctx = ctx;
        this.current_Person_id = current_Person_id;

        if (ctx instanceof UserEventInterface)
            interfaceListener = (UserEventInterface) ctx;

        // Retrieve the current Person object using the ID
        current_Person = MainSys.getPersonById(current_Person_id);

        // Initialize the inflater
        LayoutInflater inflater = LayoutInflater.from(ctx);

        // Inflate the layout for this fragment using the binding
        binding = FragmentUserEventBinding.inflate(inflater);

        // Get the root view from the binding
        viewW = binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // recycler view
        fillRecyclerView("all");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // recycler view
        fillRecyclerView("all");

        // filtering event
        binding.userEventSpFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                view = (TextView) view;
                fillRecyclerView(((TextView) view).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // weather event
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

                            // convert weather
                            ArrayList<String> wxPhraseLongDay = new ArrayList<>();
                            ArrayList<Integer> temperatureMax = new ArrayList<>();
                            ArrayList<Integer> temperatureMin = new ArrayList<>();

                            for (int i = 2; i < 5; i++) {
                                wxPhraseLongDay.add(weather.getWxPhraseLongDay().get(i));
                                temperatureMax.add(MainSys.fahrenheitToCelsius(weather.getTemperatureMax().get(i)));
                                temperatureMin.add(MainSys.fahrenheitToCelsius(weather.getTemperatureMin().get(i)));
                            }
                            weather = new Weather(wxPhraseLongDay, temperatureMax, temperatureMin);

                            Log.d("ahmet weather json", weather.toString());

                            createShowDialogWeather(weather);

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

        // add btn event
        binding.userEventBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceListener.userEventBehavior();
            }
        });

        return viewW;
    }

    private void fillRecyclerView(String participation) {
        // recycler view related
        LinearLayoutManager layoutManager = new LinearLayoutManager(ctx);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.userEventRvEvents.setLayoutManager(layoutManager);
        // fill the RecyclerView
        EventsAdapter adapter = new EventsAdapter(ctx, "userEventFragment", MainSys.getEventsAsArrayListFromParticipation(participation, current_Person), current_Person);
        binding.userEventRvEvents.setAdapter(adapter);
        // recycler view related end
    }

    public void updateUserEventFragment() {
        String participator = binding.userEventSpFilter.getSelectedItem().toString();
        fillRecyclerView(participator);
//        MainSys.msg(ctx,"fragment transaction");
    }

    public void createShowDialogWeather(Weather weather) {
        Dialog customDialog = new Dialog(ctx);
        customDialog.setContentView(R.layout.dialog_weather);
        Button btnClose = customDialog.findViewById(R.id.dialogWeather_Btn_Close);

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (int i = 1; i <= weather.getWxPhraseLongDay().size(); i++) {
            // Create the ID dynamically
            int textViewId = getResources().getIdentifier("dialogWeather_Tv" + i + "_Tv", "id", ctx.getPackageName());

            // Create the ID dynamically
            int imageViewId = getResources().getIdentifier("dialogWeather_Iv" + i + "_Img", "id", ctx.getPackageName());

            // Find the TextView by the dynamically created ID
            TextView tv = customDialog.findViewById(textViewId);
            // Find the ImageView by the dynamically created ID
            ImageView iv = customDialog.findViewById(imageViewId);

            //image related
            String imgName = "weather_cloud";
            if (weather.getWxPhraseLongDay().get(i - 1).toString().equalsIgnoreCase("PM Thunderstorms"))
                imgName = "weather_thunder";
            else if (weather.getWxPhraseLongDay().get(i - 1).toString().equalsIgnoreCase("Partly Cloudy"))
                imgName = "weather_cloudy";
            else {
                imgName = "weather_cloud";
            }
            int imgId = MainSys.convertImageNameToId(ctx, imgName);
            iv.setImageResource(imgId);

            // tv related


            // Format the next date using the formatter
            LocalDate nextDate = currentDate.plusDays(i - 1);
            String formattedDate = nextDate.format(formatter);

            String output = formattedDate.toString();
            output += "\n";
            output += weather.getWxPhraseLongDay().get(i - 1).toString();
            output += "\nMax Temperature: ";
            output += weather.getTemperatureMax().get(i - 1).toString();
            output += "°C\nMin Temperature: ";
            output += weather.getTemperatureMin().get(i - 1).toString();
            output += "°C";


            tv.setText(output);

        }

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });

        customDialog.show();
    }
}