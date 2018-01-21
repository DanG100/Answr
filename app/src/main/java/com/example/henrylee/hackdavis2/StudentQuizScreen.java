package com.example.henrylee.hackdavis2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentQuizScreen extends AppCompatActivity implements TextWatcher, RadioGroup.OnCheckedChangeListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static String FIRST_NAME = "firstname";
    public static String LAST_NAME = "lastname";
    public static String EMAIL = "email";
    public static String SID = "sid";
    public static String CLASS_CODE = "classcode";


    private String firstName;
    private  String lastName;
    private  String email;
    private  String sid;
    private  String classCode;


    private QuizQuestionsForStudent quizQuestions = new QuizQuestionsForStudent();
    private ArrayList<EditText> shortAnswerAnswers = new ArrayList<EditText>();
    RadioGroup[] mcButtonGroups;
    StudentResponseData studentResponse;
    List<Boolean> mcOrShortAnswer;
    List<String> shortAnswers;
    List<Integer> choiceIndexes;

    public QuizQuestionsForStudent getQuizQuestions() {

        return quizQuestions;
    }


    public void setQuizQuestions(QuizQuestionsForStudent quizQuestions) {
        //this.quizQuestions = quizQuestions;

        //TEMPORARY DATA FOR TESTING
        String[] ansStrings = {"answer1","answer 2","answer 3","answer 4","answer 5"};
        MCQuestion testMC1 = new MCQuestion(ansStrings, 2);
        MCQuestion[] testMCArray1 = {null, testMC1, null, null, null};
        this.quizQuestions.setMultipleChoiceQuestions(testMCArray1);
        boolean[] mcOrShortAnswer = {true, false, true, true, true};
        this.quizQuestions.setMcOrShortAnswer(mcOrShortAnswer);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_quiz_screen);

        Intent intent = getIntent();
        this.firstName = intent.getStringExtra(FIRST_NAME);
        this.lastName = intent.getStringExtra(LAST_NAME);
        this.email = intent.getStringExtra(EMAIL);
        this.sid = intent.getStringExtra(SID);
        this.classCode = intent.getStringExtra(CLASS_CODE);

        setQuizQuestions(null);

        mcButtonGroups = new RadioGroup[quizQuestions.getMcOrShortAnswer().length];

        for(int i = 0; i < quizQuestions.getMcOrShortAnswer().length; i++){

            TextView questionNumber = new TextView(this);
            questionNumber.setText("Question " + i);
            ((LinearLayout) findViewById(R.id.student_quiz_page_linear_layout)).addView(questionNumber);
            mcButtonGroups[i] = new RadioGroup(this);
            mcButtonGroups[i].setOnCheckedChangeListener(this);
            if(!quizQuestions.getMcOrShortAnswer()[i]){
                for(int j = 0; j < 5; j++){
                    RadioButton button = new RadioButton(this);
                    button.setText(quizQuestions.getMultipleChoiceQuestions()[i].getChoices()[j]);
                    mcButtonGroups[i].addView(button);

                }
                shortAnswerAnswers.add(null);
                ((LinearLayout) findViewById(R.id.student_quiz_page_linear_layout)).addView(mcButtonGroups[i]);
            }
            else{
                mcButtonGroups[i] = null;
                shortAnswerAnswers.add(new EditText(this));
                shortAnswerAnswers.get(i).setHint("Your Answer Here");
                shortAnswerAnswers.get(i).addTextChangedListener(this);

                //IDs are saved as ints but I don't know how they convert the string to a number so I can't do this line.
                //This makes getting the input a bit tricky. Have fun!
                //textInput.setId("quiz_text_input")
                ((LinearLayout) findViewById(R.id.student_quiz_page_linear_layout)).addView(shortAnswerAnswers.get(i));
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                studentResponse = new StudentResponseData(mcOrShortAnswer, shortAnswers, choiceIndexes);
                for(String str : studentResponse.getShortAnswers())
                {
                    Log.d("up",str);
                }
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference();
                reference = reference.child(classCode).child(sid);
                studentResponse.email = email;
                studentResponse.first = firstName;
                studentResponse.last = lastName;
                reference.setValue(studentResponse);

            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        updateData();

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        updateData();
    }

    public void updateData() {
        Log.d("mc","question paused saving answers");
        mcOrShortAnswer = new ArrayList<Boolean>(5); //true for mc, false for sa
        shortAnswers = new ArrayList<String>(5);
        choiceIndexes = new ArrayList<Integer>(5);
        for(int i = 0; i <mcButtonGroups.length; i++)
        {
            mcOrShortAnswer.add(true);
            shortAnswers.add("");
            choiceIndexes.add(1);
        }

        //mcButtonGroups
        int choiceIndex;
        for(int i = 0; i < mcButtonGroups.length; i++){
            if(mcButtonGroups[i] == null ){
                //if the question is a short answer
                String ansStr = shortAnswerAnswers.get(i).getText().toString();
                mcOrShortAnswer.set(i, false);
                shortAnswers.set(i, ansStr);
                choiceIndexes.set(i, -1);
                Log.d("student","fuck ");
                Log.d("student",ansStr);
            }
            else{
                //if the question is a multiple choice
                choiceIndex  = mcButtonGroups[i].indexOfChild(mcButtonGroups[i].findViewById(mcButtonGroups[i].getCheckedRadioButtonId()));
                mcOrShortAnswer.set(i, true);
                shortAnswers.set(i, "");
                choiceIndexes.set(i, choiceIndex);
                Log.d("student","short ans up");

            }
        }



        //PUSH THIS DATA TO THE CLOUD
        //PUSH THIS DATA TO THE CLOUD
        //PUSH THIS DATA TO THE CLOUD
        //PUSH THIS DATA TO THE CLOUD
        //PUSH THIS DATA TO THE CLOUD
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_quiz_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_student_quiz_screen, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
