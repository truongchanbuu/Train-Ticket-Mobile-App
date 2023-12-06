package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trainticketproject.models.Ticket;

import java.util.List;

@Dao
public interface TicketDAO {
    @Query("SELECT COUNT(*) FROM Ticket WHERE trainId = :trainId AND status = 'AVAILABLE'")
    LiveData<Integer> getAvailableTicketCountByTrainId(int trainId);

    @Query("SELECT * FROM Ticket WHERE ticketId = :id")
    LiveData<Ticket> getTicketById(int id);

    @Insert
    void insertTicket(Ticket ticket);

    @Insert
    void insertMultipleTickets(List<Ticket> sampleTickets);
}
