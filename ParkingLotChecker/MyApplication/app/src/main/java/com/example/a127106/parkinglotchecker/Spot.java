package com.example.a127106.parkinglotchecker;

import java.io.Serializable;

/**
 * Created by 124827 on 10/8/2016.
 */
public class Spot implements Serializable {
    int mMake;
    int mModel;
    int mLicense;
    int mState;
    int btSpotCounter = 0;
    int ytSpotCounter = 0;
    int etSpotCounter = 0;
    int esSpotCounter = 0;
    int gpSpotCounter = 0;
    String mSpot;
    String mLotName;
    boolean mChecked;
    boolean mPresent;
    boolean mDifferent;

    public Spot(int resLotId, int car, int spot) {
        // Generate a new spot number based on the parking lot
        if (resLotId == 0) {
            mLotName = "Ballard";
            mSpot = "BT0" + spot;
        }
        else if (resLotId == 1) {
            mLotName = "Young";
            mSpot = "YT0" + spot;
        }
        else if (resLotId == 2) {
            mLotName = "East";
            mSpot = "ET0" + spot;
        }
        else if (resLotId == 3) {
            mLotName = "Eagles";
            mSpot = "ES0" + spot;
        }
        else if (resLotId == 4) {
            mLotName = "Girls";
            mSpot = "GP0" + spot;
        }

        // Populate student car information
        switch (car) {
            case 1:
                mMake = R.string.corvette;
                mModel = R.string.zr1;
                mLicense = R.string.l1;
                mState = R.string.s1;
                break;

            case 2:
                mMake = R.string.ferrari;
                mModel = R.string.italia;
                mLicense = R.string.l2;
                mState = R.string.s3;
                break;

            case 3:
                mMake = R.string.toyota;
                mModel = R.string.camry;
                mLicense = R.string.l3;
                mState = R.string.s4;
                break;

            case 4:
                mMake = R.string.ford;
                mModel = R.string.mustang;
                mLicense = R.string.l4;
                mState = R.string.s2;
                break;

            case 5:
                mMake = R.string.dodge;
                mModel = R.string.ram;
                mLicense = R.string.l5;
                mState = R.string.s1;
                break;

            case 6:
                mMake = R.string.ford;
                mModel = R.string.mustang;
                mLicense = R.string.l6;
                mState = R.string.s3;
                break;

            case 7:
                mMake = R.string.toyota;
                mModel = R.string.camry;
                mLicense = R.string.l7;
                mState = R.string.s1;
                break;

            case 8:
                mMake = R.string.ford;
                mModel = R.string.mustang;
                mLicense = R.string.l8;
                mState = R.string.s4;
                break;

            case 9:
                mMake = R.string.dodge;
                mModel = R.string.ram;
                mLicense = R.string.l9;
                mState = R.string.s2;
                break;

            case 10:
                mMake = R.string.ferrari;
                mModel = R.string.italia;
                mLicense = R.string.l10;
                mState = R.string.s3;
                break;

            case 11:
                mMake = R.string.toyota;
                mModel = R.string.camry;
                mLicense = R.string.l11;
                mState = R.string.s1;
                break;

            case 12:
                mMake = R.string.dodge;
                mModel = R.string.ram;
                mLicense = R.string.l12;
                mState = R.string.s3;
                break;

            case 13:
                mMake = R.string.dodge;
                mModel = R.string.ram;
                mLicense = R.string.l13;
                mState = R.string.s4;
                break;

            case 14:
                mMake = R.string.ford;
                mModel = R.string.mustang;
                mLicense = R.string.l14;
                mState = R.string.s2;
                break;

            case 15:
                mMake = R.string.toyota;
                mModel = R.string.camry;
                mLicense = R.string.l15;
                mState = R.string.s1;
                break;

            case 16:
                mMake = R.string.ford;
                mModel = R.string.mustang;
                mLicense = R.string.l16;
                mState = R.string.s3;
                break;

            case 17:
                mMake = R.string.dodge;
                mModel = R.string.ram;
                mLicense = R.string.l17;
                mState = R.string.s2;
                break;

            case 18:
                mMake = R.string.ferrari;
                mModel = R.string.italia;
                mLicense = R.string.l18;
                mState = R.string.s4;
                break;

            case 19:
                mMake = R.string.toyota;
                mModel = R.string.camry;
                mLicense = R.string.l19;
                mState = R.string.s2;
                break;

            case 20:
                mMake = R.string.ford;
                mModel = R.string.mustang;
                mLicense = R.string.l20;
                mState = R.string.s1;
                break;

            case 21:
                mMake = R.string.toyota;
                mModel = R.string.camry;
                mLicense = R.string.l21;
                mState = R.string.s4;
                break;

            case 22:
                mMake = R.string.ford;
                mModel = R.string.mustang;
                mLicense = R.string.l22;
                mState = R.string.s2;
                break;

            case 23:
                mMake = R.string.dodge;
                mModel = R.string.ram;
                mLicense = R.string.l23;
                mState = R.string.s1;
                break;

            case 24:
                mMake = R.string.ford;
                mModel = R.string.mustang;
                mLicense = R.string.l24;
                mState = R.string.s3;
                break;

            case 25:
                mMake = R.string.toyota;
                mModel = R.string.camry;
                mLicense = R.string.l25;
                mState = R.string.s1;
                break;

            default:
                Lot.mCar = 0;
        }
        mChecked = false;
        mPresent = false;
        mDifferent = false;
    }

    // Getters and setters
    public String getmLotName() { return mLotName; }

    public boolean getmChecked() {
        return mChecked;
    }
    public void setmChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }

    public boolean getmPresent() {
        return mPresent;
    }
    public void setmPresent(boolean mPresent) {
        this.mPresent = mPresent;
    }

    public boolean getmDifferent() {
        return mDifferent;
    }
    public void setmDifferent(boolean mDifferent) {
        this.mDifferent = mDifferent;
    }

    public int getmMake() { return mMake; }
    public int getmModel() { return mModel; }
    public int getmLicense() { return mLicense; }
    public int getmState() { return mState; }
    public String getmSpot() { return mSpot; }
}