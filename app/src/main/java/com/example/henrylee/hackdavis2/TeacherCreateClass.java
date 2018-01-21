package com.example.henrylee.hackdavis2;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeacherCreateClass.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeacherCreateClass#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherCreateClass extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText teacherText;
    private String[] teacherData = {"Name", "Class Name"};

    public TeacherCreateClass() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherCreateClass.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherCreateClass newInstance(String param1, String param2) {
        TeacherCreateClass fragment = new TeacherCreateClass();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_teacher_create_class, container, false);
        ((Button) view.findViewById(R.id.teacher_next_but_blah)).setOnClickListener(this);

        return view;
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

    public void blahButtonPressed(View view){


        //Intent intent = new Intent("Teacher Data", teacherData);
    }

    @Override
    public void onClick(View view) {
        teacherData[0] = ((EditText) view.getRootView().findViewById(R.id.teacher_name_text_input)).getText().toString();
        teacherData[1] = ((EditText) view.getRootView().findViewById(R.id.teacher_class_name_text_input)).getText().toString();
        Log.d("data",teacherData[0]);

        //THROW TEACHER DATA INTO FILE HERE
        Intent intent = new Intent(getContext(), TeacherCreateQuiz.class);
        intent.putExtra("TEACHER",teacherData);
        startActivity(intent);
        if(getActivity()!=null) {
            Intent intentService = new Intent(getActivity(), TeacherNotificationService.class);
            getActivity().startService(intentService);
        }

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
}
