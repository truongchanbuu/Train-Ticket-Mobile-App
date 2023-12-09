package com.example.trainticketproject.daos;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.trainticketproject.models.Station;

@Dao
public interface StationDAO {
    @Query("SELECT * FROM Station WHERE stationId = :id")
    Station getStationById(Long id);
}
