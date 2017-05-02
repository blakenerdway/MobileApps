package com.example.a127106.crimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 127106 on 10/19/2016.
 */
public class CrimeListFragment extends Fragment {

   private RecyclerView _mCrimeListView;
   private CrimeAdapter _mAdapter;

   @Override
   public void onCreate(Bundle sis){
      // Connects to the model!!!!!!!!!!!!!!!!!!
      // cannot use findViewById();
      super.onCreate(sis);
   }


   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {

      // Inflate the fragment crime list
      View v = inflater.inflate(R.layout.fragment_crime_list, container, false);

      _mCrimeListView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);

      // Dynamically create new Linear layout and send this current activity (getActivity())
      _mCrimeListView.setLayoutManager(new LinearLayoutManager(getActivity()));

      // Get means get instance of the singleton
      _mAdapter = new CrimeAdapter(CrimeLab.get().getAllCrimes());
      _mCrimeListView.setAdapter(_mAdapter);

      // Inflate the layout for this fragment
      return v;
   }

   @Override
   public void onResume(){
      super.onResume();
      CrimeLab cl = CrimeLab.get();
      List<Crime> crimes = cl.getAllCrimes();

      if (_mAdapter == null){
         _mAdapter = new CrimeAdapter(crimes);
         _mCrimeListView.setAdapter(_mAdapter);
      }

      else
         _mAdapter.notifyDataSetChanged();
   }

   //--------------------------------------------------------
   // View Holder for the crime fragments
   //--------------------------------------------------------
   private class CrimeHolder extends RecyclerView.ViewHolder
           implements View.OnClickListener{
      private TextView _mTitleTextView;
      private TextView _mDate;
      private CheckBox _mSolved;
      private Crime _mCrime;

      public CrimeHolder (View itemView) {
         super(itemView);
         itemView.setOnClickListener(this);
         _mTitleTextView = (TextView) itemView.findViewById(R.id.crimeListCrimeTitle);
         _mDate = (TextView) itemView.findViewById(R.id.crimeListDateText);
         _mSolved = (CheckBox) itemView.findViewById(R.id.crimeListSolvedCbx);
      }

      public void bindCrimeTextData(Crime crime){
         _mCrime = crime;
         _mTitleTextView.setText(_mCrime.getTitle());
         _mDate.setText(_mCrime.getDate().toString());
         _mSolved.setChecked(_mCrime.isSolved());
      }

      @Override
      public void onClick(View v){
         Intent i = CrimePager.startNewCrimePagerIntent(getActivity(), _mCrime.getId());
         startActivity(i);
      }
   }

   //--------------------------------------------------------
   // Adapter, used when the recycler view needs to create a
   // new object
   //--------------------------------------------------------
   private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
      private List<Crime> _mAdapterCrimeList;

      public CrimeAdapter(List<Crime> crimes){
         _mAdapterCrimeList = crimes;
      }

      @Override
      public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType){
         LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

         // Tells adapter which layout to use
         View view = layoutInflater.inflate(R.layout.crime_list_item, parent, false);
         return new CrimeHolder(view);
      }

      @Override
      public void onBindViewHolder(CrimeHolder holder, int position){
         // List.get(position) is the same as array[position]
         Crime crime = _mAdapterCrimeList.get(position);
         holder.bindCrimeTextData(crime);
      }

      @Override
      public int getItemCount(){
         return _mAdapterCrimeList.size();
      }
   }
}