package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.repositories.TicketRepository;

public class TicketViewModel extends AndroidViewModel {
    private final TicketRepository ticketRepository;
    public TicketViewModel(@NonNull Application application) {
        super(application);
        ticketRepository = new TicketRepository(application);
    }

    public LiveData<Integer> getAvailableTicketCountByTrainId(int trainId) {
        return ticketRepository.getAvailableTicketCountByTrainId(trainId);
    }

    public LiveData<Ticket> getTicketById(int ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }
}
