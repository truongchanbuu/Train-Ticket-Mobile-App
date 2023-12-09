package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.TrainDAO;
import com.example.trainticketproject.databases.TrainRoomDatabase;
import com.example.trainticketproject.models.Train;

import java.util.List;

public class TrainRepository {
    private final TrainDAO trainDAO;

    public TrainRepository(Application application) {
        TrainRoomDatabase trainDatabase = TrainRoomDatabase.getDatabase(application);
        trainDAO = trainDatabase.trainDAO();
    }

    public LiveData<Train> getTrainById(Long trainId) {
        return trainDAO.getTrainById(trainId);
    }

    public void insertTrain(Train train) {
        trainDAO.insertTrain(train);
    }

    public void insertMultipleTrains(List<Train> trains) {
        trainDAO.insertMultipleTrains(trains);
    }

    public LiveData<List<Train>> getAllTrains() {
        return trainDAO.getAllTrains();
    }
}
