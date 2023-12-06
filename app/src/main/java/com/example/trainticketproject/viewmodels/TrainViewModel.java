package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.repositories.TicketRepository;
import com.example.trainticketproject.repositories.TrainRepository;

public class TrainViewModel extends AndroidViewModel {
    private final TrainRepository trainRepository;
    private final TicketRepository ticketRepository;

    public TrainViewModel(@NonNull Application application) {
        super(application);
        trainRepository = new TrainRepository(application);
        ticketRepository = new TicketRepository(application);
    }

    public LiveData<Train> getTrainById(int trainId) {
        return trainRepository.getTrainById(trainId);
    }

    public LiveData<Integer> getAvailableSeatsCount(int trainId) {
        return ticketRepository.getAvailableTicketCountByTrainId(trainId);
    }
}
