package com.example.trainticketproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Station {
    @PrimaryKey(autoGenerate = true)
    private int stationId;
    private String stationName;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
