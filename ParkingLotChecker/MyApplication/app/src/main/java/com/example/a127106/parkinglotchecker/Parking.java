package com.example.a127106.parkinglotchecker;

import java.io.Serializable;

/**
 * Created by jason on 10/9/2016.
 */

public class Parking implements Serializable {
    private int currentLot = 0;

    private Lot[] parkingLots;

    public Parking(){
        parkingLots = new Lot[] {
                new Lot(MainActivity.mBallardInt),
                new Lot(MainActivity.mYoungInt),
                new Lot(MainActivity.mEastInt),
                new Lot(MainActivity.mEaglesInt),
                new Lot(MainActivity.mGirlsInt),
        };
    }

    // Getters and setters

    public Lot getCurrentLot(int lot) { return parkingLots[lot]; }
    public void setLot(int lot, Lot passedLot) { parkingLots[lot] = passedLot; }

}