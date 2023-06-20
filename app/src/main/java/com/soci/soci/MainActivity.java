package com.soci.soci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.soci.soci.Adapter.EventsAdapter;
import com.soci.soci.Adapter.PageSwiperAdapter;
import com.soci.soci.Business.MainSys;
import com.soci.soci.Model.Person;
import com.soci.soci.Service.MyService;
import com.soci.soci.Ui.EventsFragment;
import com.soci.soci.Ui.UserEventFragment;
import com.soci.soci.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements UserEventFragment.UserEventInterface, EventsFragment.EventsFragmentInterface, EventsAdapter.RvAdapterInterface {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    ActivityMainBinding binding;
    PageSwiperAdapter pagerAdapter;
    IntentFilter intentFilter;
    Person current_Person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // hide title bar
        getSupportActionBar().hide();
        setContentView(view);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // lock orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // get current user
        Intent receivedIntent = getIntent();
        int current_Person_id = receivedIntent.getIntExtra("person_id", -1);
        current_Person = MainSys.getPersonById(current_Person_id);

        // service related
        // register for service with action name
        intentFilter = new IntentFilter();
        intentFilter.addAction("RANDOM_MESSAGE_GENERATED");

        // associate service with its receiver
        registerReceiver(broadcastReceiver, intentFilter);

        // create intent to send data
        Intent intent = new Intent(getBaseContext(), MyService.class);

        // start service with thread
        MyService.enqueueWork(MainActivity.this, intent);
        // service related end


        // swiper opertaions
        performSwiperOperation(current_Person_id);
    }

    private void performSwiperOperation(int current_Person_id) {
        // swiper related
        pagerAdapter = new PageSwiperAdapter(this, current_Person_id);
        binding.mainVpViewPager.setAdapter(pagerAdapter);

        TabLayout.Tab tab = binding.mainTlTabLayout.newTab();
        tab.setIcon(R.drawable.tab_0);
        binding.mainTlTabLayout.addTab(tab);

        tab = binding.mainTlTabLayout.newTab();
        tab.setIcon(R.drawable.tab_1);
        binding.mainTlTabLayout.addTab(tab);

        tab = binding.mainTlTabLayout.newTab();
        tab.setIcon(R.drawable.tab_2);
        binding.mainTlTabLayout.addTab(tab);

        // swiper related
        binding.mainVpViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.mainTlTabLayout.selectTab(binding.mainTlTabLayout.getTabAt(position));
            }
        });

        // swiper related
        binding.mainTlTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.mainVpViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do nothing
            }
        });

        // swiper related start from events fragment
        binding.mainVpViewPager.setCurrentItem(1, false);
    }

    @Override
    public void userEventBehavior() {
        pagerAdapter.performInterfaceOperations("update user event fragment rv");
        pagerAdapter.performInterfaceOperations("update events fragment rv");
    }

    @Override
    public void eventsFragmentBehavior() {
        pagerAdapter.performInterfaceOperations("update user event fragment rv");
        pagerAdapter.performInterfaceOperations("update events fragment rv");
    }

    @Override
    public void rvAdapterBehavior(String fragmentName) {
        if (fragmentName.equalsIgnoreCase("userEventFragment")) {
            pagerAdapter.performInterfaceOperations("update events fragment rv");
            pagerAdapter.performInterfaceOperations("update user event fragment rv");

        } else if (fragmentName.equalsIgnoreCase("eventsFragment")) {
            pagerAdapter.performInterfaceOperations("update events fragment rv");
            pagerAdapter.performInterfaceOperations("update user event fragment rv");
        }
    }

    // executed when broadcast message is received from service
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String output = intent.getStringExtra("message");
            MainSys.msg(MainActivity.this, output + current_Person.getName() + ".");
        }
    };
}