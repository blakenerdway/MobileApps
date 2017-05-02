package com.example.a127106.myapplication;

/**
 * Created by 127106 on 10/7/2016.
 */
public class Number {
   private int _mMainNumber;
   private int _mSecondaryNumber;


   public Number(int main, int second) {
      _mMainNumber = main;
      _mSecondaryNumber = second;
   }

   public int getMainNumber(){
     return _mMainNumber;
   }

   public int getSecondaryNumber(){
      return _mSecondaryNumber;
   }

   public void setMainNumber(int main){
      _mMainNumber = main;
   }

   public void setSecondaryNumber(int second){
      _mSecondaryNumber = second;
   }

   public String displayNumber(){
      return _mMainNumber + "." + _mSecondaryNumber;
   }

}
