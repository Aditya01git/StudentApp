package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder> {

    private List<String> homeworkList;

    public HomeworkAdapter(List<String> homeworkList) {
        this.homeworkList = homeworkList;
    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homework, parent, false);
        return new HomeworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        String homework = homeworkList.get(position);
        holder.textViewHomework.setText(homework);
    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }

    public static class HomeworkViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHomework;

        public HomeworkViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHomework = itemView.findViewById(R.id.textViewHomework);
        }
    }
}
