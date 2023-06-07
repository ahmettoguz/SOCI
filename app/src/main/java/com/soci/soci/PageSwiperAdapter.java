package com.soci.soci;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageSwiperAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;
    Context ctx;

    public PageSwiperAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        ctx = (Context) fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new UserEventFragment(ctx);
            case 1:
                return new EventsFragment(ctx);
            case 2:
                return new UserInformationFragment(ctx);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
