package com.example.a127106.parkinglotchecker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LotActivity extends AppCompatActivity {

    private TextView _mParkingLotText;
    private TextView _mSpotText;
    private TextView _mMakeText;
    private TextView _mModelText;
    private TextView _mLicense;
    private TextView _mStateText;

    private Button _mSubmitBtn;
    private Button _mNextBtn;
    private Button _mPrevBtn;

    private RadioGroup _mRadioGrp;

    private int _mLotNumber;

    private Lot _mLot;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Activity2LotNumber", _mLotNumber);
        savedInstanceState.putSerializable("Activity2LotObj", _mLot);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot);

        _mLotNumber = getIntent().getIntExtra("LotNumber", -1);

        if (_mLotNumber == -1){
            finish();
        }


        // If the phone was turned from landscape to portrait or vice versa
        if (savedInstanceState != null) {
            _mLotNumber = savedInstanceState.getInt("Activity2LotNumber", -99);
            _mLot = (Lot) savedInstanceState.getSerializable("Activity2LotObj");
        }

        // If the phone wasn't flipped and this activity was called
        else{
            _mLot = (Lot) getIntent().getSerializableExtra("LotObject");
        }

        if (_mLot == null){
            _mLot = new Lot(_mLotNumber);
        }

        _mParkingLotText = (TextView) findViewById(R.id.parkingLotTitle);
        _mSubmitBtn = (Button) findViewById(R.id.submitBtn);


        _mNextBtn = (Button) findViewById(R.id.next);
        _mPrevBtn = (Button) findViewById(R.id.previous);

        _mSpotText = (TextView) findViewById(R.id.spotText);
        _mMakeText = (TextView) findViewById(R.id.make);
        _mModelText = (TextView) findViewById(R.id.model);
        _mLicense = (TextView) findViewById(R.id.licensePlate);
        _mStateText = (TextView) findViewById(R.id.state);

        _mRadioGrp = (RadioGroup) findViewById(R.id.choices);

        _mParkingLotText.setText(_mLot.getCurrentSpot().getmLotName());

        setValues();

        // This is only called so that way as soon as a user clicks a lot, it immediately can throw
        // an exception if they go back
        insertIntentData();

        if (!_mLot.getDone())
            _mSubmitBtn.setVisibility(View.GONE);
        else
            _mNextBtn.setEnabled(false);

        _mRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Submit button needs to be reset if user changes something
                if (_mLot.getSubmit()){
                    _mLot.setSubmit(false);
                    Toast.makeText(LotActivity.this, "Remember to Press Submit!",
                                    Toast.LENGTH_LONG).show();
                }

                // Not checking for -1 results in a bug because of Android's programming.
                // Android will enter this function 2 times on RadioGroup.clearCheck, once with
                // the previous id (resulting in the booleans being set to true), and the second
                // with a -1; that's why -1 must be accounted for
                if (checkedId == -1) {
                    _mLot.getCurrentSpot().setmPresent(false);
                    _mLot.getCurrentSpot().setmChecked(false);
                    _mLot.getCurrentSpot().setmDifferent(false);
                }
                else {
                    if (checkedId == R.id.radioButton1) {
                        _mLot.getCurrentSpot().setmPresent(true);
                        _mLot.getCurrentSpot().setmChecked(false);
                        _mLot.getCurrentSpot().setmDifferent(false);
                    } else if (checkedId == R.id.radioButton2) {
                        _mLot.getCurrentSpot().setmPresent(false);
                        _mLot.getCurrentSpot().setmChecked(true);
                        _mLot.getCurrentSpot().setmDifferent(false);
                    } else if (checkedId == R.id.radioButton3) {
                        _mLot.getCurrentSpot().setmPresent(false);
                        _mLot.getCurrentSpot().setmChecked(false);
                        _mLot.getCurrentSpot().setmDifferent(true);
                    }
                }
                insertIntentData();
            }
        });

        // On Click listeners
        _mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_mLot.checkIfAllChecked()) {
                    Toast.makeText(LotActivity.this, "Data submitted successfully!",
                            Toast.LENGTH_LONG).show();
                    _mLot.setSubmit(true);
                    insertIntentData();
                }
                else{
                    Toast.makeText(LotActivity.this, "Error: Not all spots have been checked!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        _mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mLot.goToNext();
                setValues();
                // Show submit button if on 5th spot
                if (_mLot.getDone()){
                    _mSubmitBtn.setVisibility(View.VISIBLE);
                    _mNextBtn.setEnabled(false);
                }
            }
        });

        _mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_mLot.getDone()){
                    _mSubmitBtn.setVisibility(View.GONE);
                }
                _mLot.goToPrev();
                _mNextBtn.setEnabled(true);
                setValues();
            }
        });
    }

    // Set whichever radio button was pressed before
    public void setValues(){
        if (_mLot.getCurrentSpot().getmPresent()) {
            _mRadioGrp.check(R.id.radioButton1);
        }
        else if (_mLot.getCurrentSpot().getmChecked()) {
            _mRadioGrp.check(R.id.radioButton2);
        }
        else if (_mLot.getCurrentSpot().getmDifferent()) {
            _mRadioGrp.check(R.id.radioButton3);
        }
        else{
            _mRadioGrp.clearCheck();
        }
        _mSpotText.setText(_mLot.getCurrentSpot().getmSpot());
        _mMakeText.setText(_mLot.getCurrentSpot().getmMake());
        _mModelText.setText(_mLot.getCurrentSpot().getmModel());
        _mStateText.setText(_mLot.getCurrentSpot().getmState());
        _mLicense.setText(_mLot.getCurrentSpot().getmLicense());
    }

    // Used to go back to the main activity
    public void insertIntentData(){
        Intent i = new Intent();
        // need the lot to be serialized to do this
        i.putExtra("ParkingLot", _mLot);
        setResult(RESULT_OK, i);
    }

    // Used from the main activity
    public static Intent newParkingLotIntent(Context current, int lotNum, Lot lotObject) {
        Intent i = new Intent(current, LotActivity.class);
        i.putExtra("LotNumber", lotNum);
        i.putExtra("LotObject", lotObject);
        return i;
    }
}
