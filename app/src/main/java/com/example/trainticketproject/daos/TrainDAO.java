package com.example.trainticketproject.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trainticketproject.models.Train;

import java.util.List;

@Dao
public interface TrainDAO {
    @Insert
    void insertTrain(Train train);

    @Update
    void updateTrain(Train train);

    @Delete
    void deleteTrain(Train train);

    @Query("SELECT * FROM Train WHERE trainId = :id")
    Train getTrainById(int id);

    @Query("SELECT * FROM Train")
    List<Train> getAllTrains();

    @Insert
    void insertMultipleTrains(List<Train> trains);
}
