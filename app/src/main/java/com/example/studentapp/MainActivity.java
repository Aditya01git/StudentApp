package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonTimetable, buttonHomework, buttonResources, buttonMentalHealth, buttonCollaboration, buttonTeacherCommunication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTimetable = findViewById(R.id.buttonTimetable);
        buttonHomework = findViewById(R.id.buttonHomework);
        buttonResources = findViewById(R.id.buttonResources);


        buttonTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TimetableActivity.class));
            }
        });

        // Existing button onClick listeners
        buttonHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeworkActivity.class));
            }
        });
        buttonResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResourcesActivity.class));
            }
        });


    }
}
