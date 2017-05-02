package com.example.a127106.crimeapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by 127106 on 11/14/2016.
 */
public class DatePickerFragment extends DialogFragment {

   private DatePicker _date;

   public static DatePickerFragment newInstance(Date pDate)
   {
      Bundle args = new Bundle();
      args.putSerializable("SentDate", pDate);

      DatePickerFragment dpf = new DatePickerFragment();
      dpf.setArguments(args);
      return dpf;
   }

   @Override
   public Dialog onCreateDialog(Bundle sis){

      Date date = (Date) getArguments().getSerializable("SentDate");

      Calendar cal = Calendar.getInstance();
      cal.setTime(date);

      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH);
      int day = cal.get(Calendar.DAY_OF_MONTH);

      View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date_picker, null);
      _date = (DatePicker) v.findViewById(R.id.datePicker);
      _date.init(year, month, day, null);

      return new AlertDialog.Builder(getActivity())
              .setView(v)
              .setTitle("Test")
              .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                    int y = _date.getYear();
                    int m = _date.getMonth();
                    int d = _date.getDayOfMonth();
                    Date calDate = new GregorianCalendar(y, m, d).getTime();

                    sendResult(Activity.RESULT_OK, calDate);
                 }
              })
              .create();
   }

   private void sendResult(int resultCode, Date date){
      if (getTargetFragment() == null)
         return;

      Intent i = new Intent();
      i.putExtra("Returned Date", date);
      getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
   }
}
