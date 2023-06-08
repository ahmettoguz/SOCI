package com.soci.soci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.soci.soci.Adapter.PageSwiperAdapter;
import com.soci.soci.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
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
        PageSwiperAdapter pagerAdapter = new PageSwiperAdapter(this);
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
}