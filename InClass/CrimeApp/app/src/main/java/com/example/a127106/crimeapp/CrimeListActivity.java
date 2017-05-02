package com.example.a127106.crimeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by 127106 on 10/28/2016.
 */
public class CrimeListActivity extends FragmentActivity {
   private Crime crime;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_crime);

      FragmentManager fm = getSupportFragmentManager();

      Fragment myFragment = fm.findFragmentById(R.id.fragmentContainer);

      if (myFragment == null){
         myFragment = new CrimeListFragment();
         fm.beginTransaction().add(R.id.fragmentContainer, myFragment).commit();
      }
   }
}
