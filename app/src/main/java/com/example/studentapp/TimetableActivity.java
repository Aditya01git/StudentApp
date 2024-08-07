package com.example.studentapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TimetableActivity extends AppCompatActivity {

    private EditText editTextClass, editTextTime;
    private RecyclerView recyclerViewTimetable;
    private TimetableAdapter adapter;
    private List<TimetableEntry> timetableList;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        editTextClass = findViewById(R.id.editTextClass);
        editTextTime = findViewById(R.id.editTextTime);
        Button buttonSave = findViewById(R.id.buttonSave);
        recyclerViewTimetable = findViewById(R.id.recyclerViewTimetable);

        sharedPreferences = getSharedPreferences("TimetablePrefs", MODE_PRIVATE);
        gson = new Gson();
        loadTimetable();

        adapter = new TimetableAdapter(timetableList);
        recyclerViewTimetable.setAdapter(adapter);
        recyclerViewTimetable.setLayoutManager(new LinearLayoutManager(this));

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTimetable();
            }
        });
    }

    private void saveTimetable() {
        String className = editTextClass.getText().toString();
        String time = editTextTime.getText().toString();

        if (className.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please enter both class name and time", Toast.LENGTH_SHORT).show();
            return;
        }

        TimetableEntry newEntry = new TimetableEntry(className, time);
        timetableList.add(newEntry);
        adapter.notifyItemInserted(timetableList.size() - 1);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(timetableList);
        editor.putString("timetable", json);
        editor.apply();

        editTextClass.setText("");
        editTextTime.setText("");
    }

    private void loadTimetable() {
        String json = sharedPreferences.getString("timetable", null);
        Type type = new TypeToken<ArrayList<TimetableEntry>>() {}.getType();
        timetableList = gson.fromJson(json, type);

        if (timetableList == null) {
            timetableList = new ArrayList<>();
        }
    }

    public static class TimetableEntry {
        private String className;
        private String time;

        public TimetableEntry(String className, String time) {
            this.className = className;
            this.time = time;
        }

        public String getClassName() {
            return className;
        }

        public String getTime() {
            return time;
        }
    }
}

