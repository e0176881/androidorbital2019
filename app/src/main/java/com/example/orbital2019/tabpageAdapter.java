package com.example.orbital2019;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class tabpageAdapter extends FragmentStatePagerAdapter {


    String[] tabarray = new String[]{"Tab1Fragment","Tab2Fragment"};
    Integer tabNumber =2;
    public tabpageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position){
        return tabarray[position];
    }

    @Override
    public Fragment getItem(int position) {
       switch(position)
       {
           case 0:
               Tab1Fragment tab1 =  new Tab1Fragment();
               return tab1;
           case 1:
               Tab2Fragment tab2 =  new Tab2Fragment();
               return tab2;
       }
       return null;
    }

    @Override
    public int getCount() {
        return tabNumber;
    }


}
