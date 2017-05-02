package com.example.a127106.parkinglotchecker;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static final int mBallardInt = 0;
    public static final int mYoungInt = 1;
    public static final int mEastInt = 2;
    public static final int mEaglesInt = 3;
    public static final int mGirlsInt = 4;

    private Button _mBallardBtn;
    private Button _mYoungBtn;
    private Button _mEastBtn;
    private Button _mEaglesBtn;
    private Button _mGirlsBtn;

    private TextView _mBallardExc;
    private TextView _mYoungExc;
    private TextView _mEastExc;
    private TextView _mEaglesExc;
    private TextView _mGirlsExc;
    private TextView _mBallardNotice;
    private TextView _mYoungNotice;
    private TextView _mEastNotice;
    private TextView _mEaglesNotice;
    private TextView _mGirlsNotice;

    // p stands for present, m for missing, d for different
    private TextView _mBallardPStat;
    private TextView _mBallardMStat;
    private TextView _mBallardDStat;
    private TextView _mYountPStat;
    private TextView _mYoungMStat;
    private TextView _mYoungDStat;
    private TextView _mEastPStat;
    private TextView _mEastMStat;
    private TextView _mEastDStat;
    private TextView _mEaglesStat;
    private TextView _mEaglesMStat;
    private TextView _mEaglesDStat;
    private TextView _mGirlsPStat;
    private TextView _mGirlsMStat;
    private TextView _mGirlsDStat;

    private ImageView _mBallardChk;
    private ImageView _mYoungChk;
    private ImageView _mEastChk;
    private ImageView _mEaglesChk;
    private ImageView _mGirlsChk;

    // NOTE: In the following section, two booleans for each lot must be present. If there's only 1,
    // then the asterisk could be turned on even if the button hasn't been checked.
    private boolean _mBallardNotDone;
    private boolean _mYoungNotDone;
    private boolean _mEastNotDone;
    private boolean _mEaglesNotDone;
    private boolean _mGirlsNotDone;

    private boolean _mBallardSubmit;
    private boolean _mYoungSubmit;
    private boolean _mEastSubmit;
    private boolean _mEaglesSubmit;
    private boolean _mGirlsSubmit;

    // Lots used for the
    private Parking _mAllLots;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("Object", _mAllLots);

        savedInstanceState.putBoolean("BallardSubmit", _mBallardSubmit);
        savedInstanceState.putBoolean("BallardDone",   _mBallardNotDone);
        savedInstanceState.putBoolean("YoungSubmit",   _mYoungSubmit);
        savedInstanceState.putBoolean("YoungDone",     _mYoungNotDone);
        savedInstanceState.putBoolean("EastSubmit",    _mEastSubmit);
        savedInstanceState.putBoolean("EastDone",      _mEastNotDone);
        savedInstanceState.putBoolean("EaglesSubmit",  _mEaglesSubmit);
        savedInstanceState.putBoolean("EaglesDone",    _mEaglesNotDone);
        savedInstanceState.putBoolean("GirlsSubmit",   _mGirlsSubmit);
        savedInstanceState.putBoolean("GirlsDone",     _mGirlsNotDone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If the phone was turned from landscape to portrait or vice versa
        if (savedInstanceState != null) {
            _mAllLots = (Parking) savedInstanceState.getSerializable("Object");
            initializeView();
            initalizeBooleans(savedInstanceState);
        }
        else{
            _mAllLots = new Parking();
            initializeView();
        }

        showExceptions();

        // Listeners for the different parking lot buttons
        _mBallardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startParkingLotIntent(mBallardInt);
            }
        });

        _mYoungBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startParkingLotIntent(mYoungInt);
            }
        });

        _mEastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startParkingLotIntent(mEastInt);
            }
        });

        _mEaglesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startParkingLotIntent(mEaglesInt);
            }
        });

        _mGirlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startParkingLotIntent(mGirlsInt);
            }
        });

    }

    public void initializeView(){
        _mBallardBtn = (Button) findViewById(R.id.ballardBtn);
        _mYoungBtn = (Button) findViewById(R.id.youngBtn);
        _mEastBtn = (Button) findViewById(R.id.eastBtn);
        _mEaglesBtn = (Button) findViewById(R.id.eaglesBtn);
        _mGirlsBtn = (Button) findViewById(R.id.girlsBtn);

        _mBallardExc = (TextView) findViewById(R.id.ballardExc);
        _mYoungExc = (TextView) findViewById(R.id.youngExc);
        _mEastExc = (TextView) findViewById(R.id.eastExc);
        _mEaglesExc = (TextView) findViewById(R.id.eaglesExc);
        _mGirlsExc = (TextView) findViewById(R.id.girlsExc);


        _mBallardNotice = (TextView) findViewById(R.id.ballardNotice);
        _mYoungNotice = (TextView) findViewById(R.id.youngNotice);
        _mEastNotice = (TextView) findViewById(R.id.eastNotice);
        _mEaglesNotice = (TextView) findViewById(R.id.eaglesNotice);
        _mGirlsNotice = (TextView) findViewById(R.id.girlsNotice);


        _mBallardChk = (ImageView) findViewById(R.id.ballardChkMark);
        _mBallardChk.setVisibility(View.INVISIBLE);
        _mYoungChk = (ImageView) findViewById(R.id.youngChkMark);
        _mYoungChk.setVisibility(View.INVISIBLE);
        _mEastChk = (ImageView) findViewById(R.id.eastChkMark);
        _mEastChk.setVisibility(View.INVISIBLE);
        _mEaglesChk = (ImageView) findViewById(R.id.eaglesChkMark);
        _mEaglesChk.setVisibility(View.INVISIBLE);
        _mGirlsChk = (ImageView) findViewById(R.id.girlsChkMark);
        _mGirlsChk.setVisibility(View.INVISIBLE);


        _mBallardPStat = (TextView) findViewById(R.id.textView);
        _mBallardMStat = (TextView) findViewById(R.id.textView2);
        _mBallardDStat = (TextView) findViewById(R.id.textView3);
        _mYountPStat = (TextView) findViewById(R.id.textView4);
        _mYoungMStat = (TextView) findViewById(R.id.textView5);
        _mYoungDStat = (TextView) findViewById(R.id.textView6);
        _mEastPStat = (TextView) findViewById(R.id.textView7);
        _mEastMStat = (TextView) findViewById(R.id.textView8);
        _mEastDStat = (TextView) findViewById(R.id.textView9);
        _mEaglesStat = (TextView) findViewById(R.id.textView10);
        _mEaglesMStat = (TextView) findViewById(R.id.textView11);
        _mEaglesDStat = (TextView) findViewById(R.id.textView12);
        _mGirlsPStat = (TextView) findViewById(R.id.textView13);
        _mGirlsMStat = (TextView) findViewById(R.id.textView14);
        _mGirlsDStat = (TextView) findViewById(R.id.textView15);
    }

    // Get the booleans out of the savedInstanceState
    public void initalizeBooleans(Bundle sis){
        _mBallardNotDone = sis.getBoolean("BallardDone");
        _mYoungNotDone = sis.getBoolean("YoungDone");
        _mEastNotDone = sis.getBoolean("EastDone");
        _mEaglesNotDone = sis.getBoolean("EaglesDone");
        _mGirlsNotDone = sis.getBoolean("GirlsDone");

        _mBallardSubmit = sis.getBoolean("BallardSubmit");
        _mYoungSubmit = sis.getBoolean("YoungSubmit");
        _mEastSubmit = sis.getBoolean("EastSubmit");
        _mEaglesSubmit = sis.getBoolean("EaglesSubmit");
        _mGirlsSubmit = sis.getBoolean("GirlsSubmit");
    }

    // Initialize booleans to false
    public void initializeBooleans(){
        _mBallardNotDone = false;
        _mYoungNotDone = false;
        _mEastNotDone = false;
        _mEaglesNotDone = false;
        _mGirlsNotDone = false;

        _mBallardSubmit = false;
        _mYoungSubmit = false;
        _mEastSubmit = false;
        _mEaglesSubmit = false;
        _mGirlsSubmit = false;
    }

    // Show the parking lot screen
    public void startParkingLotIntent(int lotNum) {
        // getCurrentLot is called so that way if a lot has already been called and returned to this
        // activity, it can be sent back
        Intent anotherActivity = LotActivity.newParkingLotIntent(MainActivity.this, lotNum,
                                                                _mAllLots.getCurrentLot(lotNum));

        // Put the lot number as the request code
        startActivityForResult(anotherActivity, lotNum);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent){
        // Switch statement is used to know which parking lot just finished
        if(resultCode != RESULT_OK)
            return;
        else {
            if (returnedIntent == null) {
                return;
            } else {
                switch (requestCode) {
                    case (mBallardInt):
                        _mAllLots.setLot(mBallardInt, (Lot) returnedIntent.getSerializableExtra("ParkingLot"));
                        _mBallardSubmit = _mAllLots.getCurrentLot(mBallardInt).getSubmit();
                        _mBallardNotDone = !_mBallardSubmit;
                        break;

                    case (mYoungInt):
                        _mAllLots.setLot(mYoungInt, (Lot) returnedIntent.getSerializableExtra("ParkingLot"));
                        _mYoungSubmit = _mAllLots.getCurrentLot(mYoungInt).getSubmit();
                        _mYoungNotDone = !_mYoungSubmit;
                        break;

                    case (mEastInt):
                        _mAllLots.setLot(mEastInt, (Lot) returnedIntent.getSerializableExtra("ParkingLot"));
                        _mEastSubmit = _mAllLots.getCurrentLot(mEastInt).getSubmit();
                        _mEastNotDone = !_mEastSubmit;
                        break;

                    case (mEaglesInt):
                        _mAllLots.setLot(mEaglesInt, (Lot) returnedIntent.getSerializableExtra("ParkingLot"));
                        _mEaglesSubmit = _mAllLots.getCurrentLot(mEaglesInt).getSubmit();
                        _mEaglesNotDone = !_mEaglesSubmit;
                        break;

                    case (mGirlsInt):
                        _mAllLots.setLot(mGirlsInt, (Lot) returnedIntent.getSerializableExtra("ParkingLot"));
                        _mGirlsSubmit = _mAllLots.getCurrentLot(mGirlsInt).getSubmit();
                        _mGirlsNotDone = !_mGirlsSubmit;
                        break;
                }
            }
        }
        showExceptions();
    }

    public void showExceptions(){
        // Ballard section
        if (_mBallardNotDone && !_mBallardSubmit){
            _mBallardChk.setEnabled(false);
            _mBallardExc.setText(R.string.exception);
            _mBallardNotice.setText(R.string.notice);
        }
        else if (!_mBallardNotDone && _mBallardSubmit){
            _mBallardChk.setVisibility(View.VISIBLE);
            _mBallardExc.setText(R.string.empty);
            _mBallardNotice.setText(R.string.empty);
        }
        else{
            _mBallardChk.setVisibility(View.GONE);
            _mBallardExc.setText(R.string.empty);
            _mBallardNotice.setText(R.string.empty);
        }

        // Young section
        if (_mYoungNotDone && !_mYoungSubmit){
            _mYoungChk.setVisibility(View.GONE);
            _mYoungExc.setText(R.string.exception);
            _mYoungNotice.setText(R.string.notice);
        }
        else if (!_mYoungNotDone && _mYoungSubmit){
            _mYoungChk.setVisibility(View.VISIBLE);
            _mYoungExc.setText(R.string.empty);
            _mYoungNotice.setText(R.string.empty);
        }
        else{
            _mYoungChk.setVisibility(View.GONE);
            _mYoungExc.setText(R.string.empty);
            _mYoungNotice.setText(R.string.empty);
        }

        // East lot section
        if (_mEastNotDone && !_mEastSubmit){
            _mEastChk.setVisibility(View.GONE);
            _mEastExc.setText(R.string.exception);
            _mEastNotice.setText(R.string.notice);
        }
        else if (!_mEastNotDone && _mEastSubmit){
            _mEastChk.setVisibility(View.VISIBLE);
            _mEastExc.setText(R.string.empty);
            _mEastNotice.setText(R.string.empty);
        }
        else{
            _mEastChk.setVisibility(View.GONE);
            _mEastExc.setText(R.string.empty);
            _mEastNotice.setText(R.string.empty);
        }

        // Eagles lot section
        if (_mEaglesNotDone && !_mEaglesSubmit){
            _mEaglesChk.setVisibility(View.GONE);
            _mEaglesExc.setText(R.string.exception);
            _mEaglesNotice.setText(R.string.notice);
        }
        else if (!_mEaglesNotDone && _mEaglesSubmit){
            _mEaglesChk.setVisibility(View.VISIBLE);
            _mEaglesExc.setText(R.string.empty);
            _mEaglesNotice.setText(R.string.empty);
        }
        else{
            _mEaglesChk.setVisibility(View.GONE);
            _mEaglesExc.setText(R.string.empty);
            _mEaglesNotice.setText(R.string.empty);
        }

        // Girls' garage
        if (_mGirlsNotDone && !_mGirlsSubmit){
            _mGirlsChk.setVisibility(View.GONE);
            _mGirlsExc.setText(R.string.exception);
            _mGirlsNotice.setText(R.string.notice);
        }
        else if (!_mGirlsNotDone && _mGirlsSubmit){
            _mGirlsChk.setVisibility(View.VISIBLE);
            _mGirlsExc.setText(R.string.empty);
            _mGirlsNotice.setText(R.string.empty);
        }
        else{
            _mGirlsChk.setVisibility(View.GONE);
            _mGirlsExc.setText(R.string.empty);
            _mGirlsNotice.setText(R.string.empty);
        }
        setStats();
    }

    // Set all the statistics to be displayed;
    // Stats are empty if the submit button hasn't been pressed
    public void setStats(){
        for (int i = 0; i < 5; i++)
        {
            switch(i){
                case(mBallardInt):
                    if(_mAllLots.getCurrentLot(i).getSubmit()){
                        _mBallardPStat.setText("Present:   " + _mAllLots.getCurrentLot(i).getTotalPresent() + "/5");
                        _mBallardMStat.setText("Missing:   " + _mAllLots.getCurrentLot(i).getTotalMissing() + "/5");
                        _mBallardDStat.setText("Different: " + _mAllLots.getCurrentLot(i).getTotalDifferent() + "/5");
                    }
                    else {
                        _mBallardPStat.setText(R.string.empty);
                        _mBallardMStat.setText(R.string.empty);
                        _mBallardDStat.setText(R.string.empty);
                    }
                    break;
                case(mYoungInt):
                    if(_mAllLots.getCurrentLot(i).getSubmit()){
                        _mYountPStat.setText("Present:   " + _mAllLots.getCurrentLot(i).getTotalPresent() + "/5");
                        _mYoungMStat.setText("Missing:   " + _mAllLots.getCurrentLot(i).getTotalMissing() + "/5");
                        _mYoungDStat.setText("Different: " +_mAllLots.getCurrentLot(i).getTotalDifferent() + "/5");
                    }
                    else {
                        _mYountPStat.setText(R.string.empty);
                        _mYoungMStat.setText(R.string.empty);
                        _mYoungDStat.setText(R.string.empty);
                    }
                    break;
                case(mEastInt):
                    if(_mAllLots.getCurrentLot(i).getSubmit()){
                        _mEastPStat.setText("Present:   " + _mAllLots.getCurrentLot(i).getTotalPresent() + "/5");
                        _mEastMStat.setText("Missing:   " + _mAllLots.getCurrentLot(i).getTotalMissing() + "/5");
                        _mEastDStat.setText("Different: " + _mAllLots.getCurrentLot(i).getTotalDifferent() + "/5");
                    }
                    else {
                        _mEastPStat.setText(R.string.empty);
                        _mEastMStat.setText(R.string.empty);
                        _mEastDStat.setText(R.string.empty);
                    }
                    break;
                case(mEaglesInt):
                    if(_mAllLots.getCurrentLot(i).getSubmit()){
                        _mEaglesStat.setText("Present:    " + _mAllLots.getCurrentLot(i).getTotalPresent() + "/5");
                        _mEaglesMStat.setText("Missing:   " + _mAllLots.getCurrentLot(i).getTotalMissing() + "/5");
                        _mEaglesDStat.setText("Different: " + _mAllLots.getCurrentLot(i).getTotalDifferent() + "/5");
                    }
                    else {
                        _mEaglesStat.setText(R.string.empty);
                        _mEaglesMStat.setText(R.string.empty);
                        _mEaglesDStat.setText(R.string.empty);
                    }
                    break;
                case(mGirlsInt):
                    if(_mAllLots.getCurrentLot(i).getSubmit()){
                        _mGirlsPStat.setText("Present:   " + _mAllLots.getCurrentLot(i).getTotalPresent() + "/5");
                        _mGirlsMStat.setText("Missing:   " + _mAllLots.getCurrentLot(i).getTotalMissing() + "/5");
                        _mGirlsDStat.setText("Different: " + _mAllLots.getCurrentLot(i).getTotalDifferent() + "/5");
                    }
                    else {
                        _mGirlsPStat.setText(R.string.empty);
                        _mGirlsMStat.setText(R.string.empty);
                        _mGirlsDStat.setText(R.string.empty);
                    }
                    break;
            }
        }
    }
}
