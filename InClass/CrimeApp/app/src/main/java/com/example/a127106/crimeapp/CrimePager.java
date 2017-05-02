package com.example.a127106.crimeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.UUID;

public class CrimePager extends AppCompatActivity {

   private ViewPager _myPager;
   private List<Crime> _myCrimes;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.crime_pager);

      _myPager = (ViewPager) findViewById(R.id.crimePager);

      _myCrimes = CrimeLab.get().getAllCrimes();

      FragmentManager fm = getSupportFragmentManager();

      _myPager.setAdapter(new FragmentPagerAdapter(fm) {
         @Override
         public Fragment getItem(int position) {
            return CrimeFragment.newCrimeFragmentInstance(_myCrimes.get(position).getId());
         }

         @Override
         public int getCount() {
            return _myCrimes.size();
         }
      });

      UUID id = (UUID) getIntent().getSerializableExtra("sentCrime");

      for (int i = 0; i < _myCrimes.size(); i++)
         if (_myCrimes.get(i).getId().equals(id)) {
            _myPager.setCurrentItem(i);
            break;
      }
   }

   public static Intent startNewCrimePagerIntent(Context c, UUID id){
      Intent i = new Intent(c, CrimePager.class);
      i.putExtra("sentCrime", id);
      return i;
   }
}
