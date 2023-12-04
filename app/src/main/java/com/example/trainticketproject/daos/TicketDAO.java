package com.example.trainticketproject.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trainticketproject.models.Ticket;

import java.util.List;

@Dao
public interface TicketDAO {
    @Query("SELECT COUNT(*) FROM Ticket WHERE trainId = :trainId AND status = 'AVAILABLE'")
    int getAvailableTicketCountByTrainId(int trainId);

    @Insert
    void insertTicket(Ticket ticket);
}
