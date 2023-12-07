package com.example.trainticketproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.trainticketproject.utils.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

@Entity
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    private Integer ticketId;
    private Integer uid;
    private int trainId;
    private int seatNumber;
    private LocalDateTime issuedDate;
    private Status status;

    public Ticket(Integer uid, int trainId, int seatNumber, LocalDateTime issuedDate, Status status) {
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String toJson() {
        Gson gson = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();
        }
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", uid=" + uid +
                ", trainId=" + trainId +
                ", seatNumber=" + seatNumber +
                ", issuedDate=" + issuedDate +
                ", status=" + status +
                "}";
    }
}
