package com.example.henrylee.hackdavis2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link multipleChoiceQuestion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link multipleChoiceQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class multipleChoiceQuestion extends Fragment implements TextWatcher{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SEC_NUM = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String[] options;
    private int correctIndex;
    private String keywords;

    public multipleChoiceQuestion() {
        // Required empty public constructor
        options = new String[6];
    }

    public static multipleChoiceQuestion newInstance(int position) {
        multipleChoiceQuestion fragment = new multipleChoiceQuestion();
        Bundle args = new Bundle();
        args.putInt(SEC_NUM, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getArguments().getInt(SEC_NUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        Log.d("mc","view created");
        return inflater.inflate(R.layout.fragment_multiple_choice_question, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("mc","activity created");
        super.onActivityCreated(savedInstanceState);
        ( (RadioGroup)getView().findViewById(R.id.radio_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateData();
            }
        });
        ((EditText)getView().findViewById(R.id.option1_text)).addTextChangedListener(this);
        ((EditText)getView().findViewById(R.id.option2_text)).addTextChangedListener(this);
        ((EditText)getView().findViewById(R.id.option3_text)).addTextChangedListener(this);
        ((EditText)getView().findViewById(R.id.option4_text)).addTextChangedListener(this);
        ((EditText)getView().findViewById(R.id.option5_text)).addTextChangedListener(this);
        ((EditText)getView().findViewById(R.id.option_short_text)).addTextChangedListener(this);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        updateData();
    }

    public void updateData() {
        Log.d("mc","question paused saving answers");
        RadioGroup rg = getView().findViewById(R.id.radio_group);
        int textIndex = 0;
        int radioIndex = 0;
        for(int i = 0; i < rg.getChildCount(); i++)
        {
            if(rg.getChildAt(i) instanceof EditText)
            {
                String text = ((EditText) rg.getChildAt(i)).getText().toString();
                Log.d("quiz",text);
                options[textIndex] = text;
                textIndex++;
            }
            else if(rg.getChildAt(i) instanceof RadioButton)
            {
                if ( ((RadioButton) rg.getChildAt(i)).isChecked() )
                {
                    Log.d("quiz",Integer.toString(radioIndex));
                    if(radioIndex == 5)//short ans
                    {
                        keywords = ((EditText) getView().findViewById(R.id.option_short_text)) .getText().toString();
                        correctIndex = -1;

                    }
                    else
                    {
                        correctIndex = radioIndex;
                    }
                }
                radioIndex++;
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }


    public String getKeywords() {
        return keywords;
    }

}
