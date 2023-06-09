package com.soci.soci.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.soci.soci.Ui.EventsFragment;
import com.soci.soci.Ui.UserEventFragment;
import com.soci.soci.Ui.UserInformationFragment;

public class PageSwiperAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;
    Context ctx;

    int current_Person_id;

    public PageSwiperAdapter(@NonNull FragmentActivity fragmentActivity, int current_Person_id) {
        super(fragmentActivity);
        this.current_Person_id = current_Person_id;
        ctx = (Context) fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new UserEventFragment(ctx, current_Person_id);
            case 1:
                return new EventsFragment(ctx, current_Person_id);
            case 2:
                return new UserInformationFragment(ctx, current_Person_id);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
