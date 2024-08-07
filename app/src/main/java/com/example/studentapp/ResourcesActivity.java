package com.example.studentapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResourcesActivity extends AppCompatActivity {

    private EditText editTextResourceTitle, editTextResourceLink;
    private RecyclerView recyclerViewResources;
    private ResourcesAdapter resourcesAdapter;
    private List<Resource> resourceList;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        editTextResourceTitle = findViewById(R.id.editTextResourceTitle);
        editTextResourceLink = findViewById(R.id.editTextResourceLink);
        Button buttonAddResource = findViewById(R.id.buttonAddResource);
        recyclerViewResources = findViewById(R.id.recyclerViewResources);

        // Initialize SharedPreferences and Gson
        sharedPreferences = getSharedPreferences("ResourcesPrefs", MODE_PRIVATE);
        gson = new Gson();

        // Load resources from SharedPreferences
        loadResources();

        // Setup RecyclerView
        recyclerViewResources.setLayoutManager(new LinearLayoutManager(this));
        resourcesAdapter = new ResourcesAdapter(resourceList);
        recyclerViewResources.setAdapter(resourcesAdapter);

        // Set click listener for Add Resource button
        buttonAddResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addResource();
            }
        });
    }

    // Method to add a new resource
    private void addResource() {
        String title = editTextResourceTitle.getText().toString();
        String link = editTextResourceLink.getText().toString();

        if (!title.isEmpty() && !link.isEmpty()) {
            resourceList.add(new Resource(title, link));
            saveResources();
            resourcesAdapter.notifyDataSetChanged();
            editTextResourceTitle.setText("");
            editTextResourceLink.setText("");
        }
    }

    // Method to save resources to SharedPreferences
    private void saveResources() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(resourceList);
        editor.putString("resources", json);
        editor.apply();
    }

    // Method to load resources from SharedPreferences
    private void loadResources() {
        String json = sharedPreferences.getString("resources", null);
        Type type = new TypeToken<ArrayList<Resource>>() {}.getType();
        resourceList = json != null ? gson.fromJson(json, type) : new ArrayList<>();
    }
}
