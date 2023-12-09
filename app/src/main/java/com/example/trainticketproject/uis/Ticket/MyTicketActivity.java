package com.example.trainticketproject.uis.Ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.trainticketproject.R;

public class MyTicketActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);

        recyclerView = findViewById(R.id.recyclerViewMyTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}