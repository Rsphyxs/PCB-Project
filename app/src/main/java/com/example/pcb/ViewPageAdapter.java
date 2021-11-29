package com.example.pcb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPageAdapter extends FragmentPagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return CPUFragment.newInstance();
            case 1:
                return GPUFragment.newInstance();
            case 2:
                return MoboFragment.newInstance();
            case 3:
                return RAMFragment.newInstance();
            case 4:
                return StorageFragment.newInstance();
            case 5:
                return PSUFragment.newInstance();
            case 6:
                return CaseFragment.newInstance();
            case 7:
                return FanFragment.newInstance();
            default:
                return MoboFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "CPU";
            case 1:
                return "GPU";
            case 2:
                return "Mobo";
            case 3:
                return "RAM";
            case 4:
                return "Storage";
            case 5:
                return "PSU";
            case 6:
                return "Case";
            case 7:
                return "Fan";
            default:
                return "Default";
        }
    }
}
