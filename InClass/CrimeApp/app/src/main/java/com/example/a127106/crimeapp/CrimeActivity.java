package com.example.a127106.crimeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_crime);

      FragmentManager fm = getSupportFragmentManager();

      Fragment myFragment = fm.findFragmentById(R.id.fragmentContainer);

      if (myFragment == null){
         UUID id = (UUID) getIntent().getSerializableExtra("sentCrime");
         myFragment = CrimeFragment.newCrimeFragmentInstance(id);
         fm.beginTransaction().add(R.id.fragmentContainer, myFragment).commit();
      }
   }

   public static Intent startNewIntent(Context c, UUID id){
      Intent i = new Intent(c, CrimeActivity.class);
      i.putExtra("sentCrime", id);
      return i;
   }
}
