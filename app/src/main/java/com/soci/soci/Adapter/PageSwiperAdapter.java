package com.soci.soci.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.soci.soci.Business.MainSys;
import com.soci.soci.MainActivity;
import com.soci.soci.Ui.EventsFragment;
import com.soci.soci.Ui.UserEventFragment;
import com.soci.soci.Ui.UserInformationFragment;

public class PageSwiperAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;
    Context ctx;

    UserEventFragment userEventFragment;
    EventsFragment eventsFragment;

    UserInformationFragment userInformationFragment;

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
                userEventFragment = new UserEventFragment(ctx, current_Person_id);
                return userEventFragment;
            case 1:
                eventsFragment = new EventsFragment(ctx, current_Person_id);
                return eventsFragment;
            case 2:
                userInformationFragment = new UserInformationFragment(ctx, current_Person_id);
                return userInformationFragment;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

    public void performInterfaceOperations(String operation) {
        if (operation.equalsIgnoreCase("update events fragment rv")) {
            MainSys.msg(ctx, "interface çalıştı.");
        }
        else if (operation.equalsIgnoreCase("update user event fragment rv")) {
            MainSys.msg(ctx, "interface çalıştı.1");
        }
    }

}
