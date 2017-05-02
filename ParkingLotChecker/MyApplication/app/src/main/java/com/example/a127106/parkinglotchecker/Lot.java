package com.example.a127106.parkinglotchecker;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by 124827 on 10/8/2016.
 */
public class Lot implements Serializable {
    private int currentSpot = 0;
    public static int mCar = 1;
    public static int mSpot = 0;
    private int mResLotId = 0;
    private boolean mDone = false;
    private boolean mSubmit = false;

    private Spot[] parkingSpots;

    public Lot(int resLotId) {
        mResLotId = resLotId;

        parkingSpots = new Spot[] {
                new Spot(mResLotId, mCar++, mSpot++),
                new Spot(mResLotId, mCar++, mSpot++),
                new Spot(mResLotId, mCar++, mSpot++),
                new Spot(mResLotId, mCar++, mSpot++),
                new Spot(mResLotId, mCar++, mSpot++),
        };
    }

    // Reset class values
    public void reset() {
        mCar = 0;
        mDone = false;
        mResLotId = 0;
        currentSpot = 0;
    }

    // Go back a parking space
    public void goToPrev()
    {
        setDone(false);
        currentSpot--;
        if (currentSpot < 0)
            currentSpot = 0;
    }

    // Go to next parking space
    public void goToNext()
    {
        currentSpot++;
        if (currentSpot > 4)
        {
            currentSpot = 4;
        }
        if (currentSpot == 4)
        {
            setDone(true);
        }
    }


    // Getters and setters
    public Spot getCurrentSpot() { return parkingSpots[currentSpot]; }

    public boolean getDone() {
        return mDone;
    }
    public void setDone(boolean done) {
        mDone = done;
    }

    public void setSubmit (boolean submitted) {mSubmit = submitted;}
    public boolean getSubmit() {return mSubmit; }

    public int getTotalPresent()
    {
        int total = 0;
        for(int i = 0; i < 5; i++)
            if(parkingSpots[i].getmPresent())
                total++;

        return total;
    }

    public int getTotalMissing()
    {
        int total = 0;
        for(int i = 0; i < 5; i++)
            if(parkingSpots[i].getmChecked())
                total++;

        return total;
    }

    public int getTotalDifferent()
    {
        int total = 0;
        for(int i = 0; i < 5; i++)
            if(parkingSpots[i].getmDifferent())
                total++;

        return total;
    }

    public boolean checkIfAllChecked(){
        for(int i = 0; i < 5; i++)
        {
            if (!parkingSpots[i].getmPresent() &&
                    !parkingSpots[i].getmChecked() &&
                    !parkingSpots[i].getmDifferent())
                return false;
        }
        return true;
    }
}
