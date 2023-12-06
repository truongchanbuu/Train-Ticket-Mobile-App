package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.TicketDAO;
import com.example.trainticketproject.databases.TicketRoomDatabase;
import com.example.trainticketproject.models.Ticket;

public class TicketRepository {
    private final TicketDAO ticketDAO;

    public TicketRepository(Application application) {
        TicketRoomDatabase ticketDatabase = TicketRoomDatabase.getDatabase(application);
        ticketDAO = ticketDatabase.ticketDAO();
    }

    public LiveData<Integer> getAvailableTicketCountByTrainId(int trainId) {
        return ticketDAO.getAvailableTicketCountByTrainId(trainId);
    }

    public LiveData<Ticket> getTicketById(int ticketId) {
        return ticketDAO.getTicketById(ticketId);
    }
}
