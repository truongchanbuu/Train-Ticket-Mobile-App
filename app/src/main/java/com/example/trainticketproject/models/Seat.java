package com.example.trainticketproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Seat {
    @PrimaryKey(autoGenerate = true)
    private Integer seatCode;
    private Status status;
    private int trainId;

    public int getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(int seatCode) {
        this.seatCode = seatCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
}
