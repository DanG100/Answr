package com.example.henrylee.hackdavis2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TeacherScreen extends AppCompatActivity implements com.example.henrylee.hackdavis2.TeacherCreateClass.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_screen);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
