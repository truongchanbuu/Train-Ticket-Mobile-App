package com.example.trainticketproject.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trainticketproject.daos.UserDAO;
import com.example.trainticketproject.models.User;

@Database(entities = { User.class }, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    private static UserRoomDatabase INSTANCE;

    // Create instance
}
