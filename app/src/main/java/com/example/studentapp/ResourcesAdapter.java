package com.example.studentapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ResourceViewHolder> {

    private List<Resource> resourceList;

    public ResourcesAdapter(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @NonNull
    @Override
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resource, parent, false);
        return new ResourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceViewHolder holder, int position) {
        Resource resource = resourceList.get(position);
        holder.textViewTitle.setText(resource.getTitle());
        holder.textViewLink.setText(resource.getLink());

        // Handle click event
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = resource.getLink();
                Context context = v.getContext();

                // Check if the link is a YouTube playlist link
                if (isYouTubePlaylistLink(link)) {
                    openYouTubePlaylistLink(link, context);
                }
                // Check if the link is a YouTube video link
                else if (isYouTubeVideoLink(link)) {
                    openYouTubeVideoLink(link, context);
                }
                // Otherwise, treat it as a general link (PDF, external website, etc.)
                else {
                    openGeneralLink(link, context);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return resourceList.size();
    }

    public static class ResourceViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewLink;

        public ResourceViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewLink = itemView.findViewById(R.id.textViewLink);
        }
    }

    // Helper method to check if a link is a YouTube playlist link
    private boolean isYouTubePlaylistLink(String link) {
        return link != null && link.startsWith("https://www.youtube.com/playlist?list=");
    }

    // Helper method to check if a link is a YouTube video link
    private boolean isYouTubeVideoLink(String link) {
        return link != null && link.startsWith("https://www.youtube.com/watch?v=");
    }

    // Method to open YouTube playlist link
    private void openYouTubePlaylistLink(String youtubeLink, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
        intent.setPackage("com.google.android.youtube"); // Ensures it opens in YouTube app
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // YouTube app isn't installed, open in web browser instead
            intent.setPackage(null);
            context.startActivity(intent);
        }
    }

    // Method to open YouTube video link
    private void openYouTubeVideoLink(String youtubeLink, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
        intent.putExtra("force_fullscreen", true); // Optional extra for fullscreen playback
        intent.setPackage("com.google.android.youtube"); // Ensures it opens in YouTube app
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // YouTube app isn't installed, open in web browser instead
            intent.setPackage(null);
            context.startActivity(intent);
        }
    }

    // Method to open general link (e.g., PDF, external website)
    private void openGeneralLink(String link, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Handle case where no app can handle the intent
        }
    }
}
