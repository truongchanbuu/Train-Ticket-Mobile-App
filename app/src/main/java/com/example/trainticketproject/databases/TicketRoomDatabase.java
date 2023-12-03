package com.example.trainticketproject.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trainticketproject.daos.TicketDAO;
import com.example.trainticketproject.models.Ticket;

@Database(entities = { Ticket.class }, version = 1)
public abstract class TicketRoomDatabase extends RoomDatabase {
    public abstract TicketDAO ticketDAO();
    private static TicketRoomDatabase INSTANCE;

    // Create instance
}
