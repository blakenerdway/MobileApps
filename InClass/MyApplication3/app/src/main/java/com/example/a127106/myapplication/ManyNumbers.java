package com.example.a127106.myapplication;

/**
 * Created by 127106 on 10/7/2016.
 */
public class ManyNumbers {
   private Number[] _mNumbers;
   private int _mIndex;

   public ManyNumbers(){
      _mNumbers = new Number[5];
      for (int i = 0; i < _mNumbers.length; i++){
         _mNumbers[i] = new Number(i * 2, i * 2 + 1);
      }
      _mIndex = 0;
   }

   public Number getCurrentNumber(){
      return _mNumbers[_mIndex];
   }

   public void moveToNext(){
      _mIndex = (_mIndex + 1) % _mNumbers.length;
   }

}
