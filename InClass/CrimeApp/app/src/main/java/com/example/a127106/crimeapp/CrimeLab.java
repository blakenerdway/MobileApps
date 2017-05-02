package com.example.a127106.crimeapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 127106 on 10/17/2016.
 */
public class CrimeLab {
   private static CrimeLab _sCrimeLab;
   private List<Crime> _mCrimes;

   // The whole idea of a singleton
   public static CrimeLab get(){
      if (_sCrimeLab == null){
         _sCrimeLab = new CrimeLab();
      }
      return _sCrimeLab;
   }

   private CrimeLab(){
      _mCrimes = new ArrayList<>();
      for (int i = 0; i < 100; i++){
         Crime crime = new Crime();
         crime.setTitle("Crime #" + i);
         crime.setSolved(i % 2 == 0);
         _mCrimes.add(crime);
      }
   }

   public Crime getCrime(UUID id){
      for (Crime crime : _mCrimes){
         if (crime.getId().equals(id)){
            return crime;
         }
      }
      return null;
   }

   public List<Crime> getAllCrimes(){
      return _mCrimes;
   }
}
