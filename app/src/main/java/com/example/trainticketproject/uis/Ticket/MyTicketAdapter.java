package com.example.trainticketproject.uis.Ticket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.Ticket;

import java.util.List;

public class MyTicketAdapter extends RecyclerView.Adapter<MyTicketViewHolder> {
    List<Ticket> myTickets;

    public MyTicketAdapter(List<Ticket> myTickets) {
        this.myTickets = myTickets;
    }

    @NonNull
    @Override
    public MyTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_ticket_item, parent, false);
        return new MyTicketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return myTickets.size();
    }
}
