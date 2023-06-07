package com.soci.soci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.viewpager2.widget.ViewPager2;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.soci.soci.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private PagerAdapterWrapper pagerAdapter;
    private TabLayout tabLayout;
    ActivityMainBinding binding;

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


        // swiper related
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new UserEventFragment());
        fragmentList.add(new EventsFragment());
        fragmentList.add(new UserInformationFragment());
        pagerAdapter = new PagerAdapterWrapper(this, fragmentList);
        binding.mainVpViewPager.setAdapter(pagerAdapter);

//        binding.mainTlTabLayout.addTab(binding.mainTlTabLayout.newTab().setText("Page 1"));
//        binding.mainTlTabLayout.addTab(binding.mainTlTabLayout.newTab().setText("Page 2"));
//        binding.mainTlTabLayout.addTab(binding.mainTlTabLayout.newTab().setText("Page 3"));

        TabLayout.Tab tab1 = binding.mainTlTabLayout.newTab();
        tab1.setIcon(R.drawable.soci_icon);
        binding.mainTlTabLayout.addTab(tab1);

        TabLayout.Tab tab2 = binding.mainTlTabLayout.newTab();
        tab2.setIcon(R.drawable.soci_icon);
        binding.mainTlTabLayout.addTab(tab2);

        TabLayout.Tab tab3 = binding.mainTlTabLayout.newTab();
        tab3.setIcon(R.drawable.soci_icon);
        binding.mainTlTabLayout.addTab(tab3);

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
        binding.mainVpViewPager.setCurrentItem(1,false);
    }
}