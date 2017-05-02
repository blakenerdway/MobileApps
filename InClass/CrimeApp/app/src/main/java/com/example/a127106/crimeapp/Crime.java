package com.example.a127106.crimeapp;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 127106 on 10/14/2016.
 */
public class Crime {

   private UUID _mId;
   private String  _mTitle;
   private Date _mDate;
   private boolean _mSolved;

   public Crime(){
      _mId = UUID.randomUUID();
      _mSolved = true;
      _mDate = new Date();
   }

   public UUID getId(){
      return _mId;
   }

   public void setId(UUID Id) {
      _mId = Id;
   }

   public void setTitle(String Title) {
      _mTitle = Title;
   }
   public String getTitle(){
      return _mTitle;
   }

   public Date getDate() {
      return _mDate;
   }

   public void setDate(Date _mDate) {
      this._mDate = _mDate;
   }

   public boolean isSolved() {
      return _mSolved;
   }

   public void setSolved(boolean _mSolved) {
      this._mSolved = _mSolved;
   }
}
