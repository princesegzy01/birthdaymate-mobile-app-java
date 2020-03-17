package com.example.birthdaymate;


import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

class MyAdapter extends FragmentStatePagerAdapter {

    String title[] = {"Home","Notify","Gift","Message"};

    Context context;
    int totalTabs;
    public MyAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return title[position];
        return  null;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                NotificationFragment notificationFragment = new NotificationFragment();
                return notificationFragment;
            case 2:
                GiftFragment giftFragment = new GiftFragment();
                return giftFragment;
            case 3:
                MessageFragment messageFragment = new MessageFragment();
                return messageFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
