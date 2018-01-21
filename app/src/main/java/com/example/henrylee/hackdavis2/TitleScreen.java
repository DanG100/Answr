package com.example.henrylee.hackdavis2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TitleScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        final Button studentButton = findViewById(R.id.student_button);

        final Button teacherButton = findViewById(R.id.teacher_button);

    }
    public void startStudent(View view){
        Intent intent = new Intent(this, StudentScreen.class);
        startActivity(intent);
    }

    public void startTeacher(View view) {
        Intent intent = new Intent(this, TeacherScreen.class);
        //Intent intent = new Intent(this, StudentQuizScreen.class);
        startActivity(intent);
    }
}
