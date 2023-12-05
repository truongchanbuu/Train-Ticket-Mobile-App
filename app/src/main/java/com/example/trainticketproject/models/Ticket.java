package com.example.trainticketproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.time.LocalDateTime;

@Entity
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    private int ticketId;
    private int uid;
    private int trainId;
    private int seatNumber;
    private LocalDateTime issuedDate;
    private Status status;

    public Ticket(int uid, int trainId, int seatNumber, LocalDateTime issuedDate, Status status) {
        this.uid = uid;
        this.trainId = trainId;
        this.seatNumber = seatNumber;
        this.issuedDate = issuedDate;
        this.status = status;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDateTime getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
}
