package com.example.a127106.crimeapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeFragment extends Fragment {
   private Crime _mCrime;

   private EditText _mTitleField;
   private Button _mDateBtn;
   private CheckBox _mSolvedCbx;

   @Override
   public void onCreate(Bundle sis){
      super.onCreate(sis);
      UUID myCrimeId = (UUID) getArguments().getSerializable("crimeUUID");
      _mCrime = CrimeLab.get().getCrime(myCrimeId);
   }


   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      // Connects to the view!!!!!!!!!!!!!!!!!!!!!!!!!
      View v = inflater.inflate(R.layout.fragment_crime, container, false);
      _mTitleField = (EditText) v.findViewById(R.id.crimeTitle);
      _mTitleField.setText(_mCrime.getTitle());
      _mDateBtn = (Button) v.findViewById(R.id.crimeDateBtn);
      _mSolvedCbx = (CheckBox) v.findViewById(R.id.crimeSolvedCbx);

      _mDateBtn.setText(_mCrime.getDate().toString());

      _mDateBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            DatePickerFragment dialogBox = DatePickerFragment.newInstance(_mCrime.getDate());
            dialogBox.setTargetFragment(CrimeFragment.this, 0);
            dialogBox.show(fm, "Date test");
         }
      });


      _mSolvedCbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            _mCrime.setSolved(isChecked);
         }
      });
      _mSolvedCbx.setChecked(_mCrime.isSolved());

      _mTitleField.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {
            _mCrime.setTitle(s.toString());
         }

         @Override
         public void afterTextChanged(Editable s) {

         }
      });

      // Inflate the layout for this fragment
      return v;
   }


   public static CrimeFragment newCrimeFragmentInstance(UUID myId) {
      Bundle args = new Bundle();
      args.putSerializable("crimeUUID", myId);

      CrimeFragment fragment = new CrimeFragment();
      fragment.setArguments(args);
      return fragment;
   }

   @Override
   public void onActivityResult(int requestCode , int resultCode, Intent i){
      if (resultCode != Activity.RESULT_OK)
         return;

      if(resultCode != 0) {
         _mCrime.setDate((Date) i.getSerializableExtra("DateReturned"));
         _mDateBtn.setText(_mCrime.getDate().toString());
      }
   }
}
