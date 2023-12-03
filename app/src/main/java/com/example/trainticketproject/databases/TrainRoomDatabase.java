package com.example.trainticketproject.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trainticketproject.daos.TrainDAO;
import com.example.trainticketproject.models.Train;

@Database(entities = { Train.class }, version = 1)
public abstract class TrainRoomDatabase extends RoomDatabase {
    public abstract TrainDAO trainDAO();
    private static TrainRoomDatabase INSTANCE;

    // Create instance
}
