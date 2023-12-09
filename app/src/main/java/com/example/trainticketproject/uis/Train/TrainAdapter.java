package com.example.trainticketproject.uis.Train;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.viewmodels.TrainViewModel;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TrainAdapter extends RecyclerView.Adapter<TrainViewHolder> {
    private List<Train> trainList;
    private TrainViewModel trainViewModel;
    private Long uid;

    public TrainAdapter(List<Train> trainList, TrainViewModel trainViewModel, Long uid) {
        this.trainList = trainList;
        this.trainViewModel = trainViewModel;
        this.uid = uid;
    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_item, parent, false);
        return new TrainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        Train train = trainList.get(position);

        holder.tvTripBriefInfo.setText(train.getTrip());

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String formattedPrice = numberFormat.format(train.getPrice());
        holder.tvPriceBriefInfo.setText(formattedPrice + " VND");

        holder.tvStations.setText(train.getDepartureStation() + " - " + train.getArrivalStation());

        String formattedDepartureTime = null;
        String formattedArrivalTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formattedDepartureTime = train.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            formattedArrivalTime = train.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            holder.tvTimeBriefInfo.setText(formattedDepartureTime + " - " + formattedArrivalTime);
        }

        trainViewModel.getAvailableSeatsCount(train.getTrainId()).observe((LifecycleOwner) holder.itemView.getContext(), bookedSeats -> {
            int availableSeats = train.getTotalSeats() - bookedSeats;

            if (availableSeats != 0) {
                holder.tvAvailability.setText("AVAILABLE");
            } else {
                holder.tvAvailability.setText("SOLD OUT");
            }
        });

        holder.itemView.setBackgroundColor(Color.TRANSPARENT);

        holder.itemView.setOnClickListener(v -> {
            holder.itemView.setBackgroundColor(Color.LTGRAY);

            notifyItemChanged(position);

            Intent detailTrainIntent = new Intent(v.getContext(), TrainDetailInfoActivity.class);
            detailTrainIntent.putExtra("uid", uid);
            detailTrainIntent.putExtra("trainId", train.getTrainId());

            v.getContext().startActivity(detailTrainIntent);
        });
    }

    @Override
    public int getItemCount() {
        return trainList == null ? 0 : trainList.size();
    }
}
