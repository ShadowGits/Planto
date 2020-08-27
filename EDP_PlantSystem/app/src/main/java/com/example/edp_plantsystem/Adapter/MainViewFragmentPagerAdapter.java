package com.example.edp_plantsystem.Adapter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//import com.example.edp_plantsystem.Fragments.FriendlyChatFragment;
import com.example.edp_plantsystem.Fragments.GardeningQueriesFragment;
import com.example.edp_plantsystem.Fragments.IrrigationLogFragment;

public class MainViewFragmentPagerAdapter extends FragmentPagerAdapter {

    public MainViewFragmentPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        //here will be a switch statement which will cook the fragments we will display
        switch (position) {
            case 0:
                return new IrrigationLogFragment();
            case 1:
                return new GardeningQueriesFragment();

            //TODO setup the default case
        }
        return new IrrigationLogFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Irrigation Log ";
            case 1:
                return "Search Your Queries";

            //TODO setup the default case
        }
        return "    -----";
    }
}
