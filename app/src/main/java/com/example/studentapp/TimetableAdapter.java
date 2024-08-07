package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {

    private List<TimetableActivity.TimetableEntry> timetableList;

    public TimetableAdapter(List<TimetableActivity.TimetableEntry> timetableList) {
        this.timetableList = timetableList;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, parent, false);
        return new TimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        TimetableActivity.TimetableEntry entry = timetableList.get(position);
        holder.textViewClass.setText(entry.getClassName());
        holder.textViewTime.setText(entry.getTime());
    }

    @Override
    public int getItemCount() {
        return timetableList.size();
    }

    public static class TimetableViewHolder extends RecyclerView.ViewHolder {
        TextView textViewClass;
        TextView textViewTime;

        public TimetableViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewClass = itemView.findViewById(R.id.textViewClass);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }
    }
}
