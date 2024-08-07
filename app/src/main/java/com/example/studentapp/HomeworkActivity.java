package com.example.studentapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeworkActivity extends AppCompatActivity {

    private EditText editTextHomework;
    private RecyclerView recyclerViewHomework;
    private HomeworkAdapter homeworkAdapter;
    private List<String> homeworkList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        editTextHomework = findViewById(R.id.editTextHomework);
        Button buttonAddHomework = findViewById(R.id.buttonAddHomework);
        recyclerViewHomework = findViewById(R.id.recyclerViewHomework);

        sharedPreferences = getSharedPreferences("HomeworkPrefs", MODE_PRIVATE);
        homeworkList = new ArrayList<>(sharedPreferences.getStringSet("homeworkSet", new HashSet<>()));

        homeworkAdapter = new HomeworkAdapter(homeworkList);
        recyclerViewHomework.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHomework.setAdapter(homeworkAdapter);

        buttonAddHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHomework();
            }
        });
    }

    private void addHomework() {
        String homework = editTextHomework.getText().toString().trim();
        if (TextUtils.isEmpty(homework)) {
            Toast.makeText(this, "Please enter homework details", Toast.LENGTH_SHORT).show();
            return;
        }

        homeworkList.add(homework);
        homeworkAdapter.notifyDataSetChanged();
        saveHomework();
        editTextHomework.setText("");
    }

    private void saveHomework() {
        Set<String> homeworkSet = new HashSet<>(homeworkList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("homeworkSet", homeworkSet);
        editor.apply();
    }
}
