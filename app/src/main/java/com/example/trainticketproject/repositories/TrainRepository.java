package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.TrainDAO;
import com.example.trainticketproject.databases.TrainRoomDatabase;
import com.example.trainticketproject.models.Train;

public class TrainRepository {
    private final TrainDAO trainDAO;

    public TrainRepository(Application application) {
        TrainRoomDatabase trainDatabase = TrainRoomDatabase.getDatabase(application);
        trainDAO = trainDatabase.trainDAO();
    }

    public LiveData<Train> getTrainById(int trainId) {
        return trainDAO.getTrainById(trainId);
    }
}
