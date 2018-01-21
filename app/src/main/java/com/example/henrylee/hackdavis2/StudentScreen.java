package com.example.henrylee.hackdavis2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class StudentScreen extends AppCompatActivity implements com.example.henrylee.hackdavis2.StudentSignIn.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Begin activity screen by showing student sign in screen
        setContentView(R.layout.activity_student_screen);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void startQuiz(View view){
        Intent intent = new Intent(this, StudentQuizScreen.class);
        //TODO: Get Data from form and put into StudentData.java object
        //pass that object into cloud

        //DANIEL LOOK HEREEEEEEE

        //I'm so sorry
        //good luck
        //may the odds ever be in your favor
        String first = ((EditText)(this.findViewById(R.id.student_sign_in_first))).getText().toString();
        String last = ((EditText)(this.findViewById(R.id.student_sign_in_last))).getText().toString();
        String sid = ((((EditText)(this.findViewById(R.id.student_sign_in_sid)))).getText().toString());
        String email = ((EditText)(this.findViewById(R.id.student_sign_in_email))).getText().toString();
        String classCode = (((EditText)(this.findViewById(R.id.student_sign_in_class_code))).getText().toString());



        Log.d("student",first + " " + last);
        intent.putExtra(StudentQuizScreen.FIRST_NAME,first);
        intent.putExtra(StudentQuizScreen.LAST_NAME,last);
        intent.putExtra(StudentQuizScreen.SID,sid);
        intent.putExtra(StudentQuizScreen.EMAIL,email);
        intent.putExtra(StudentQuizScreen.CLASS_CODE,classCode);
        StudentData studentData = new StudentData(first, last, sid, email, classCode);
        startActivity(intent);

    }
}
